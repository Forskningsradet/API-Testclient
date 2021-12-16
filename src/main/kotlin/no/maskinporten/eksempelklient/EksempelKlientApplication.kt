package no.maskinporten.eksempelklient

import config.JwtGrantGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.io.File
import kotlin.system.exitProcess

@SpringBootApplication
class EksempelKlientApplication

var logger: Logger = LoggerFactory.getLogger(EksempelKlientApplication::class.java)


fun main(args: Array<String>) {
    runApplication<EksempelKlientApplication>(*args)

    exampleRequest()
}

private fun createHttpHeaders(): HttpHeaders {
    val token: String = JwtGrantGenerator.getToken()

    val headers = HttpHeaders()
    headers.contentType = MediaType.APPLICATION_JSON
    headers.add("Authorization", "Bearer $token")
    return headers
}

private fun exampleRequest() {
    val requestUrl = "http://localhost:8080" //TODO: Change to desired API-endpoint
    val restTemplate = RestTemplate()
    try {
        val headers: HttpHeaders = createHttpHeaders()
        val entity = HttpEntity("parameters", headers)
        val response = restTemplate.exchange(
            requestUrl, HttpMethod.GET, entity,
            String::class.java
        )
        logger.info("Result - status (" + response.statusCode + ") has body: " + response.hasBody())
        //logger.info(response.body)
        //response.body?.let { File("output.json").writeText(it) } //TODO: Change to desired filename
    } catch (eek: Exception) {
        logger.error("** Exception: " + eek.message)
    }
    exitProcess(1)
}
