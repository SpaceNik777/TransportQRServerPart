package com.transportqr.data

import com.transportqr.data.models.ComplaintModel
import com.transportqr.data.models.EnterComplaintModel
import com.transportqr.data.models.TransportModel
import com.transportqr.data.repositories.IComplaintRepository
import com.transportqr.data.repositories.ITransportRepository
import com.transportqr.data.util.Constants.Companion.BASE_IP
import com.transportqr.data.util.Constants.Companion.BASE_URL
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

class TransportQRDatabase : IComplaintRepository, ITransportRepository {
    private val client = KMongo.createClient("mongodb://rootuser:rootpass@$BASE_IP:27017").coroutine
    private val database = client.getDatabase("TransportQRDatabase")

    private val complaintsCollection = database.getCollection<ComplaintModel>("Обращения")
    private val transportCollection = database.getCollection<TransportModel>("Транспорт")

    override suspend fun getAllComplaints(): List<ComplaintModel> = complaintsCollection.find().toList()

    override suspend fun getComplaintById(complaintID: String): ComplaintModel? {
        return complaintsCollection.findOneById(complaintID)
    }

    override suspend fun updateComplaintById(complaintID: String, complaintModel: ComplaintModel): Boolean {
        return complaintsCollection.updateOneById(ComplaintModel::id eq complaintID, complaintModel).wasAcknowledged()
    }

    override suspend fun createComplaint(enterComplaintModel: EnterComplaintModel): Boolean {
        val id = ObjectId().toString()
        val complaintNumber = complaintsCollection.countDocuments() + 1
        val transport = transportCollection.findOneById(enterComplaintModel.transportCode)
        val complaintModel = ComplaintModel(
            id,
            complaintNumber,
            enterComplaintModel.transportCode,
            enterComplaintModel.header,
            enterComplaintModel.text,
            enterComplaintModel.senderEmail,
            enterComplaintModel.imageProblemClass,
            enterComplaintModel.probability,
            BASE_URL + "get-photo/" + id,
            enterComplaintModel.photo,
            transport,
            enterComplaintModel.createDate,
            enterComplaintModel.createTime
        )
        return complaintsCollection.insertOne(complaintModel).wasAcknowledged()
    }

    override suspend fun deleteComplaintById(complaintID: String): Boolean {
        return complaintsCollection.deleteOne(ComplaintModel::id eq complaintID).wasAcknowledged()
    }

    override suspend fun getTransportById(transportID: String): TransportModel? {
        return transportCollection.findOneById(transportID)
    }

    override suspend fun getAllTransport(): List<TransportModel> = transportCollection.find().toList()

    override suspend fun createTransport(transportModel: TransportModel): Boolean {
        return transportCollection.insertOne(transportModel).wasAcknowledged()
    }

    override suspend fun updateTransportById(transportID: String, transportModel: TransportModel): Boolean {
        return transportCollection.updateOneById(TransportModel::транспортный_код eq transportID, transportModel)
            .wasAcknowledged()
    }

    override suspend fun deleteTransportById(transportID: String): Boolean {
        return transportCollection.deleteOne(TransportModel::транспортный_код eq transportID).wasAcknowledged()
    }
}