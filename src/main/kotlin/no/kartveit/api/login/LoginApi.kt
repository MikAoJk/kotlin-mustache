package no.kartveit.api.login

import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.mustache.MustacheContent
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import no.kartveit.log
import no.kartveit.model.LogginDetails
import no.kartveit.service.LoginService


fun Routing.registerLoginApi() {
    get("/login") {
        call.respond(MustacheContent("login.hbs", null))
    }
    post("/login") {
        val parameters = call.receiveParameters()
        val username = parameters["username"]
        val password = parameters["password"]

        if (username != null && password != null) {
            if (LoginService().validateUser(username, password)) {
                val loginDetails = LogginDetails(username, password)
                call.respond(MustacheContent("home.hbs", mapOf("LogginDetails" to loginDetails)))
            } else {
                log.warn("Wrong username or password")
                call.respondText("Wrong username or password", status = HttpStatusCode.Forbidden)
            }
        } else {
            log.warn("Missing username or password")
            call.respondText("Missing username or password", status = HttpStatusCode.Forbidden)
        }

    }
}
