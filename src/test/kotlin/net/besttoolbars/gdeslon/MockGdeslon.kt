package net.besttoolbars.gdeslon

import java.io.InputStream
import java.net.http.HttpRequest
import java.util.concurrent.CompletableFuture

internal class MockGdeslon(private val inputStream: InputStream) : GdeslonHttpHandler {
    override fun executeRequest(request: HttpRequest): CompletableFuture<InputStream> {
        return CompletableFuture.completedFuture(inputStream)
    }
}
