package com.transportqr.data.repositories

import com.transportqr.data.models.TransportModel

interface ITransportRepository {
    suspend fun getTransportById(transportID: String): TransportModel?
    suspend fun getAllTransport(): List<TransportModel>
    suspend fun createTransport(transportModel: TransportModel): Boolean
    suspend fun updateTransportById(transportID: String, transportModel: TransportModel): Boolean
    suspend fun deleteTransportById(transportID: String): Boolean
}