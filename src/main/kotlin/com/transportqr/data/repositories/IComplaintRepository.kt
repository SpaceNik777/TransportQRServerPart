package com.transportqr.data.repositories

import com.transportqr.data.models.ComplaintModel
import com.transportqr.data.models.EnterComplaintModel

interface IComplaintRepository {
    suspend fun getAllComplaints(): List<ComplaintModel>
    suspend fun getComplaintById(complaintID: String): ComplaintModel?
    suspend fun updateComplaintById(complaintID: String, complaintModel: ComplaintModel): Boolean
    suspend fun createComplaint(enterComplaintModel: EnterComplaintModel): Boolean
    suspend fun deleteComplaintById(complaintID: String): Boolean
}