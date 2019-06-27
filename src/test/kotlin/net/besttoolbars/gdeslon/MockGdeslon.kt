package net.besttoolbars.gdeslon

import net.besttoolbars.affiliate.core.HttpHandler
import java.io.InputStream
import java.net.http.HttpRequest
import java.util.concurrent.CompletableFuture

internal class MockGdeslon(private val inputStream: InputStream) : HttpHandler {
    override fun executeRequest(request: HttpRequest): CompletableFuture<InputStream> {
        return CompletableFuture.completedFuture(inputStream)
    }
}
