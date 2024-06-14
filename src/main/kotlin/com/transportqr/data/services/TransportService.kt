package com.transportqr.data.services

import com.transportqr.data.models.TransportModel
import com.transportqr.data.repositories.ITransportRepository

class TransportService(private val ITransportRepository: ITransportRepository) {
    suspend fun getTransportById(transportID: String): TransportModel? =
        ITransportRepository.getTransportById(transportID)

    suspend fun getAllTransport(): List<TransportModel> = ITransportRepository.getAllTransport()
    suspend fun createTransport(transportModel: TransportModel): Boolean =
        ITransportRepository.createTransport(transportModel)

    suspend fun updateTransportById(transportID: String, transportModel: TransportModel): Boolean =
        ITransportRepository.updateTransportById(transportID, transportModel)

    suspend fun deleteTransportById(transportID: String): Boolean =
        ITransportRepository.deleteTransportById(transportID)
}