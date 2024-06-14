package com.transportqr.data.requests

import com.transportqr.data.models.ComplaintModel

data class UpdateComplaintRequest(
    val id: String,
    val complaintModel: ComplaintModel
)
