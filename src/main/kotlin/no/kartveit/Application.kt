package no.kartveit

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import no.kartveit.api.login.registerLoginApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit


val log: Logger = LoggerFactory.getLogger("no.kartveit.kotlinmustache")

fun main() {
    val embeddedServer =
        embeddedServer(
            Netty,
            port = 8080,
            module = Application::module,
        )
    Runtime.getRuntime()
        .addShutdownHook(
            Thread {
                log.info("Shutting down application from shutdown hook")
                embeddedServer.stop(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS.toMillis(10))
            },
        )
    embeddedServer.start(true)
}

fun Application.module() {
    configureRouting()

    log.info("Application started")

    log.debug(" ,_     _")
    log.debug(" |\\\\_,-~/")
    log.debug(" / _  _ |    ,--.")
    log.debug("(  @  @ )   / ,-'")
    log.debug("\\  _T_/-._( (")
    log.debug(" /         `. \\")
    log.debug("/         `. \\")
    log.debug("|         _  \\ |")
    log.debug("\\ \\ ,  /      |")
    log.debug(" || |-_\\__   /")
    log.debug(" ((_/`(____,-'")
    log.debug("Maybe the cat help you with the debug")
}

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        registerLoginApi()
    }
}
