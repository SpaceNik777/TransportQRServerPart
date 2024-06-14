package com.transportqr.data.models

data class EnterComplaintModel(
    val transportCode: String,
    val header: String,
    val text: String,
    val imageProblemClass: String,
    val senderEmail: String,
    val probability: String,
    val photo: ByteArray,
    val createDate: String,
    val createTime: String
)
