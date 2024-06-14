package com.transportqr.data.requests

import com.transportqr.data.models.TransportModel

data class UpdateTransportRequest(
    val id: String,
    val transportModel: TransportModel
)
