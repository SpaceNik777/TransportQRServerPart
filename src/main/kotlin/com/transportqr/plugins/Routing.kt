package com.transportqr.plugins

import com.transportqr.routes.complaintRoute
import com.transportqr.data.TransportQRDatabase
import com.transportqr.data.services.ComplaintService
import com.transportqr.data.services.TransportService
import com.transportqr.routes.transportRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        val repository = TransportQRDatabase()
        complaintRoute(complaintService = ComplaintService(repository))
        transportRoute(transportService = TransportService(repository))
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
