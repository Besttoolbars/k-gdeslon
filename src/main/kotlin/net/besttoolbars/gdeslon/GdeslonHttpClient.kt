package net.besttoolbars.gdeslon

import net.besttoolbars.affiliate.core.HttpHandler
import java.io.InputStream
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.concurrent.CompletableFuture

class GdeslonHttpClient(private val client: HttpClient = HttpClient.newHttpClient()) : HttpHandler {
    override fun executeRequest(request: HttpRequest): CompletableFuture<InputStream> {
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
            .thenApply { it.body() }
    }
}
