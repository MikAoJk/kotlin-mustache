package no.kartveit.api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.mustache.MustacheContent
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
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
                val logginDetails = LogginDetails(username, password)
                call.respond(MustacheContent("home.hbs", mapOf("LogginDetails" to logginDetails)))
            } else {
                log.warn("Wrong username or password")
                call.respondText("Wrong username or password", status = HttpStatusCode.InternalServerError)
            }
        } else {
            log.warn("Missing username or password")
            call.respondText("Missing username or password", status = HttpStatusCode.InternalServerError)
        }

    }
}