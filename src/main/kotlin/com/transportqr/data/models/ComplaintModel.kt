package com.transportqr.data.models

import org.bson.codecs.pojo.annotations.BsonId

data class ComplaintModel(
    @BsonId
    val id: String?,
    val номер_обращения: Long?,
    val транспортный_код: String?,
    val заголовок: String?,
    val сообщение: String?,
    val email_отправителя: String?,
    val класс_фото_происшествия: String?,
    val вероятность: String?,
    val ссылка_на_фото: String?,
    val фото: ByteArray?,
    val транспортное_средство: TransportModel?,
    val дата_создания: String?,
    val время_создания: String?
)