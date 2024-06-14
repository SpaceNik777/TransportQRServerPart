package com.transportqr.controllers

import com.transportqr.data.models.EnterComplaintModel
import com.transportqr.data.requests.UpdateComplaintRequest
import com.transportqr.data.services.ComplaintService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class ComplaintController(private val complaintService: ComplaintService) {

    suspend fun getComplaintById(call: ApplicationCall, id: String) {
        val complaint = complaintService.getComplaintById(id)
        complaint?.let {
            call.respond(HttpStatusCode.OK, it)
        }?: call.respond(HttpStatusCode.OK, "There is no complaint with id $id")
    }

    suspend fun getAllComplaints(call: ApplicationCall) {
        val complaints = complaintService.getAllComplaints()
        call.respond(complaints)
    }

    suspend fun updateComplaintById(call: ApplicationCall, id: String, request: UpdateComplaintRequest) {
        if (complaintService.updateComplaintById(id, request.complaintModel)) {
            call.respond(HttpStatusCode.OK, request)
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }

    suspend fun createComplaint(call: ApplicationCall, request: EnterComplaintModel) {
        if (complaintService.createComplaint(request)) {
            call.respond(HttpStatusCode.OK, request)
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }

    suspend fun deleteComplaintById(call: ApplicationCall, id: String) {
        if (complaintService.deleteComplaintById(id)) {
            call.respond(HttpStatusCode.OK, "Complaint successfully deleted")
        } else {
            call.respond(HttpStatusCode.OK, "There is no complaint with id $id")
        }
    }

    suspend fun getPhotoById(call: ApplicationCall, id: String) {
        val complaint = complaintService.getComplaintById(id)
        val photo = complaint?.фото
        if (photo!= null) {
            call.respondBytes(
                bytes = photo,
                contentType = ContentType.Image.JPEG
            )
        } else {
            call.respond(
                HttpStatusCode.OK,
                "There is no photo with id $id"
            )
        }
    }
}