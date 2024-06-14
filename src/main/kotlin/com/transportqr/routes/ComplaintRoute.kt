package com.transportqr.routes

import com.transportqr.controllers.ComplaintController
import com.transportqr.data.models.EnterComplaintModel
import com.transportqr.data.requests.GetComplaintRequest
import com.transportqr.data.requests.DeleteComplaintRequest
import com.transportqr.data.requests.UpdateComplaintRequest
import com.transportqr.data.services.ComplaintService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.complaintRoute(complaintService: ComplaintService) {
    val complaintController = ComplaintController(complaintService)

    route("/get-complaint") {
        get {
            val complaintId = call.receive<GetComplaintRequest>().id
            complaintController.getComplaintById(call, complaintId)
        }
    }

    route("/complaints") {
        get {
            complaintController.getAllComplaints(call)
        }
    }

    route("/update-complaint") {
        post {
            val request = try {
                call.receive<UpdateComplaintRequest>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            complaintController.updateComplaintById(call, request.id, request)
        }
    }

    route("/create-complaint") {
        post {
            val request = try {
                call.receive<EnterComplaintModel>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            complaintController.createComplaint(call, request)
        }
    }

    route("/delete-complaint") {
        post {
            val request = try {
                call.receive<DeleteComplaintRequest>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            complaintController.deleteComplaintById(call, request.id)
        }
    }

    route("/get-photo/{id}") {
        get {
            val photoId = call.parameters["id"]
            complaintController.getPhotoById(call, photoId?: "")
        }
    }
}