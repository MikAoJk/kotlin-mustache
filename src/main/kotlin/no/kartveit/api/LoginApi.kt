package no.kartveit.api

import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.mustache.MustacheContent
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import no.kartveit.model.LogginDetails
import no.kartveit.service.LoginService
import org.slf4j.Logger
import org.slf4j.LoggerFactory


val log: Logger = LoggerFactory.getLogger("no.kartveit.LoginApi")


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
