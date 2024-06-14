package com.transportqr.routes

import com.transportqr.controllers.TransportController
import com.transportqr.data.models.TransportModel
import com.transportqr.data.requests.*
import com.transportqr.data.services.TransportService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.transportRoute(transportService: TransportService) {
    val transportController = TransportController(transportService)

    route("/get-transport") {
        get {
            val transportId = call.receive<GetTransportRequest>().id
            transportController.getTransportById(call, transportId)
        }
    }

    route("/transport") {
        get {
            transportController.getAllTransport(call)
        }
    }

    route("/create-transport") {
        post {
            val request = try {
                call.receive<TransportModel>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            transportController.createTransport(call, request)
        }
    }

    route("/update-transport") {
        post {
            val request = try {
                call.receive<UpdateTransportRequest>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            transportController.updateTransportById(call, request.id, request.transportModel)
        }
    }

    route("/delete-transport") {
        post {
            val request = try {
                call.receive<DeleteTransportRequest>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            transportController.deleteTransportById(call, request.id)
        }
    }
}
