package no.kartveit

import no.kartveit.application.ApplicationServer
import no.kartveit.application.createApplicationEngine
import org.slf4j.Logger
import org.slf4j.LoggerFactory


val log: Logger = LoggerFactory.getLogger("no.kartveit.Bootstrap")

fun main() {
    val applicationEngine = createApplicationEngine()

    val applicationServer = ApplicationServer(applicationEngine)
    applicationServer.start()
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