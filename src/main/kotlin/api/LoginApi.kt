package api

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.mustache.MustacheContent
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import model.LogginDetails
import service.LoginService


fun Routing.registerLoginApi() {
    get("/login") {
        call.respond(MustacheContent("login.hbs", null))
    }
    post("/login") {
        val parameters = call.receiveParameters()
        val name = parameters["name"]
        val password = parameters["password"]

        if (name != null && password != null && LoginService().validateUser(name, password)) {
            val logginDetails = LogginDetails(name, password)
            call.respond(MustacheContent("home.hbs", mapOf("LogginDetails" to logginDetails)))
        }
        else{
            call.respondText("Missing name or password", status = HttpStatusCode.InternalServerError)
        }

    }
}