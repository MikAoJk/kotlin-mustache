import application.ApplicationServer
import application.createApplicationEngine

fun main() {
    val applicationEngine = createApplicationEngine()

    val applicationServer = ApplicationServer(applicationEngine)
    applicationServer.start()
}