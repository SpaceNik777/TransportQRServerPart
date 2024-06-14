package com.transportqr.data.services

import com.transportqr.data.models.ComplaintModel
import com.transportqr.data.models.EnterComplaintModel
import com.transportqr.data.repositories.IComplaintRepository

class ComplaintService(private val IComplaintRepository: IComplaintRepository) {
    suspend fun getAllComplaints(): List<ComplaintModel> = IComplaintRepository.getAllComplaints()
    suspend fun getComplaintById(complaintID: String): ComplaintModel? =
        IComplaintRepository.getComplaintById(complaintID)

    suspend fun updateComplaintById(complaintID: String, complaintModel: ComplaintModel): Boolean =
        IComplaintRepository.updateComplaintById(complaintID, complaintModel)

    suspend fun createComplaint(enterComplaintModel: EnterComplaintModel): Boolean =
        IComplaintRepository.createComplaint(enterComplaintModel)

    suspend fun deleteComplaintById(complaintID: String): Boolean =
        IComplaintRepository.deleteComplaintById(complaintID)
}