package com.transportqr.data.models

import org.bson.codecs.pojo.annotations.BsonId

data class TransportModel(
    @BsonId
    val транспортный_код: String?,
    val бортовой_номер: String?,
    val номер_маршрута: String?
)
