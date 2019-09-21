package foobar

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.features.HttpsRedirect
import io.ktor.html.respondHtml
import io.ktor.network.tls.certificates.generateCertificate
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.*
import java.awt.Desktop
import java.io.File
import java.net.URI

fun main(args: Array<String>) {
    createTestCertificate()
    Desktop.getDesktop().browse(URI("http://localhost"))

    val environment = commandLineEnvironment(args)
    embeddedServer(Netty, environment).start(wait = true)
}

fun createTestCertificate() {
    val file = File("./test.jks")
    if (!file.exists()) {
        file.parentFile.mkdirs()
        generateCertificate(
            file = file,
            keyAlias = "testkey",
            keyPassword = "cocaxray12",
            jksPassword = "cocaxray12"
        )
    }
}

fun Application.module() {
    install(HttpsRedirect)
    install(DefaultHeaders)
    install(CallLogging)

    routing {
        get("/") {
            call.respondInfo()
        }
    }
}

suspend fun ApplicationCall.respondInfo() {
    fun TABLE.row(key: String, value: Any?) {
        tr {
            th { +key }
            td { +value.toString() }
        }
    }

    respondHtml {
        body {
            h2 {
                +"Info"
            }
            table {
                row("request.httpVersion", request.httpVersion)
                row("request.httpMethod", request.httpMethod)
                row("request.uri", request.uri)
                row("request.path()", request.path())
                row("request.host()", request.host())
            }
        }
    }
}