package com.transportqr.controllers

import com.transportqr.data.models.TransportModel
import com.transportqr.data.services.TransportService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

class TransportController(private val transportService: TransportService) {

    suspend fun getTransportById(call: ApplicationCall, id: String) {
        val transport = transportService.getTransportById(id)
        transport?.let {
            call.respond(HttpStatusCode.OK, it)
        } ?: call.respond(HttpStatusCode.OK, "There is no transport with id $id")
    }

    suspend fun getAllTransport(call: ApplicationCall) {
        val transports = transportService.getAllTransport()
        call.respond(transports)
    }

    suspend fun createTransport(call: ApplicationCall, request: TransportModel) {
        if (transportService.createTransport(request)) {
            call.respond(HttpStatusCode.OK, request)
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }

    suspend fun updateTransportById(call: ApplicationCall, id: String, request: TransportModel) {
        if (transportService.updateTransportById(id, request)) {
            call.respond(HttpStatusCode.OK, request)
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }

    suspend fun deleteTransportById(call: ApplicationCall, id: String) {
        if (transportService.deleteTransportById(id)) {
            call.respond(HttpStatusCode.OK, "Transport successfully deleted")
        } else {
            call.respond(HttpStatusCode.OK, "There is no transport with id $id")
        }
    }
}