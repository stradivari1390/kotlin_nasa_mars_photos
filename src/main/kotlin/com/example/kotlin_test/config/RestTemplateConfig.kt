package com.example.kotlin_test.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.util.StreamUtils
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.nio.charset.Charset
import java.time.Duration

@Configuration
class RestClientConfig {

    @Value("\${rest.client.timeout.connect}")
    private val connectTimeout: Long = 0

    @Value("\${rest.client.timeout.read}")
    private val readTimeout: Long = 0

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder
            .setConnectTimeout(Duration.ofMillis(connectTimeout))
            .setReadTimeout(Duration.ofMillis(readTimeout))
            .errorHandler(customErrorHandler())
            .interceptors(customInterceptor())
            .build()
    }

    fun customErrorHandler(): ResponseErrorHandler {
        return MyCustomErrorHandler()
    }

    fun customInterceptor(): ClientHttpRequestInterceptor {
        return MyCustomInterceptor()
    }
}

class MyCustomErrorHandler : ResponseErrorHandler {
    @Throws(IOException::class)
    override fun hasError(response: ClientHttpResponse): Boolean {
        val statusCode = response.statusCode
        return statusCode.is4xxClientError || statusCode.is5xxServerError
    }

    @Throws(IOException::class)
    override fun handleError(response: ClientHttpResponse) {
        val statusCode = response.statusCode
        val statusText = response.statusText
        val body = StreamUtils.copyToString(response.body, Charset.defaultCharset())

        println("HTTP error occurred with status code: $statusCode ($statusText), response body: $body")
    }
}

class MyCustomInterceptor : ClientHttpRequestInterceptor {
    override fun intercept(
        request: org.springframework.http.HttpRequest,
        body: ByteArray,
        execution: org.springframework.http.client.ClientHttpRequestExecution
    ): ClientHttpResponse {
        println("Request method: ${request.method}, Request URI: ${request.uri}")
        println("Request body: ${String(body)}")
        return execution.execute(request, body)
    }
}
