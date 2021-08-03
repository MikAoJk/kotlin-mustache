import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.mustache.Mustache
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import no.kartveit.api.registerLoginApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class LoginApiTest {

    @Test
    fun `Returns OK when loggin data is correct`() {
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
                exception<Throwable> { cause ->
                    call.respond(HttpStatusCode.InternalServerError, cause.message ?: "Unknown error")
                    throw cause
                }
            }

            with(handleRequest(HttpMethod.Post, "/login")
            {
                addHeader("Accept", "application/json")
                addHeader("Content-Type", "application/x-www-form-urlencoded")
                setBody("username=joakim&password=1234")

            }) {
                response.status() shouldBeEqualTo HttpStatusCode.OK
            }

        }
    }

    @Test
    fun `Returns InternalServerError when loggin data is wrong`() {
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
                exception<Throwable> { cause ->
                    call.respond(HttpStatusCode.InternalServerError, cause.message ?: "Unknown error")
                    throw cause
                }
            }

            with(handleRequest(HttpMethod.Post, "/login")
            {
                addHeader("Accept", "application/json")
                addHeader("Content-Type", "application/x-www-form-urlencoded")
                setBody("username=per&password=1234")

            }) {
                response.status() shouldBeEqualTo HttpStatusCode.InternalServerError
                response.content shouldBeEqualTo "Wrong username or password"
            }

        }
    }

}