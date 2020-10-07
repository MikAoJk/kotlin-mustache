package no.kartveit

import no.kartveit.application.ApplicationServer
import no.kartveit.application.createApplicationEngine
import org.slf4j.Logger
import org.slf4j.LoggerFactory


val log: Logger = LoggerFactory.getLogger("no.kartveit")

fun main() {
    val applicationEngine = createApplicationEngine()

    val applicationServer = ApplicationServer(applicationEngine)
    applicationServer.start()
    log.info("Application started")
}