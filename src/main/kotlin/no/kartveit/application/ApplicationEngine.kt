package no.kartveit.application

import no.kartveit.api.registerLoginApi
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.mustache.Mustache
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


fun createApplicationEngine(): ApplicationEngine =
    embeddedServer(Netty, 8080) {
        routing {
            registerLoginApi()
        }
        install(Mustache) {
            mustacheFactory = DefaultMustacheFactory("templates/mustache")
        }
        install(ContentNegotiation) {
            jackson {
                registerKotlinModule()
            }
        }
        install(StatusPages) {
            exception<Throwable> { cause ->
                call.respond(HttpStatusCode.InternalServerError, cause.message ?: "Unknown error")
                throw cause
            }
        }
    }