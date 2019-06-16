package net.besttoolbars.gdeslon

import java.io.InputStream
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.concurrent.CompletableFuture

interface GdeslonHttpHandler {
    fun executeRequest(request: HttpRequest): CompletableFuture<InputStream>
}

class GdeslonHttpClient(private val client: HttpClient = HttpClient.newHttpClient()) :
    GdeslonHttpHandler {
    override fun executeRequest(request: HttpRequest): CompletableFuture<InputStream> {
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
            .thenApply { it.body() }
    }
}
