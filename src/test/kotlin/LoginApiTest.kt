import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.server.application.install
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.jackson.jackson
import io.ktor.server.mustache.Mustache
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import no.kartveit.api.registerLoginApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoginApiTest {

    @Test
    fun returnsOKWhenLoggingDataIsCorrect() {
        with(TestApplicationEngine()) {
            start()

            application.routing {
                registerLoginApi()
            }

            application.install(Mustache) {
                mustacheFactory = DefaultMustacheFactory("templates/mustache")
            }
            application.install(ContentNegotiation) {
                jackson {
                    registerKotlinModule()
                }
            }
            application.install(StatusPages) {
                exception<Throwable> { call, cause ->
                    call.respondText(
                        text = "500: $cause.message ?: Unknown error",
                        status = io.ktor.http.HttpStatusCode.InternalServerError
                    )
                }
            }

            with(handleRequest(HttpMethod.Post, "/login")
            {
                addHeader("Accept", "application/json")
                addHeader("Content-Type", "application/x-www-form-urlencoded")
                setBody("username=joakim&password=1234")

            }) {
                assertEquals(response.status(), HttpStatusCode.OK)
            }

        }
    }

    @Test
    fun returnsInternalServerErrorWhenLogginDataIsWrong() {
        with(TestApplicationEngine()) {
            start()

            application.routing {
                registerLoginApi()
            }

            application.install(Mustache) {
                mustacheFactory = DefaultMustacheFactory("templates/mustache")
            }
            application.install(ContentNegotiation) {
                jackson {
                    registerKotlinModule()
                }
            }
            application.install(StatusPages) {
                exception<Throwable> { call, cause ->
                    call.respondText(
                        text = "500: $cause.message ?: Unknown error",
                        status = HttpStatusCode.InternalServerError
                    )
                }
            }

            with(handleRequest(HttpMethod.Post, "/login")
            {
                addHeader("Accept", "application/json")
                addHeader("Content-Type", "application/x-www-form-urlencoded")
                setBody("username=per&password=1234")

            }) {
                assertEquals(response.status(), HttpStatusCode.Forbidden)
                assertEquals(response.content, "Wrong username or password")
            }

        }
    }

}
