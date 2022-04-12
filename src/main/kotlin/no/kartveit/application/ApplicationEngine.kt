package no.kartveit.application

import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import no.kartveit.api.registerLoginApi
import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.server.application.install

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.jackson
import io.ktor.server.mustache.Mustache
import io.ktor.server.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText


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
            exception<Throwable> { call, cause ->
                call.respondText(
                    text = "500: $cause.message ?: Unknown error",
                    status = HttpStatusCode.InternalServerError
                )
            }
        }
    }
