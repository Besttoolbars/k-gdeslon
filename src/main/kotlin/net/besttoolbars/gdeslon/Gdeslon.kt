package net.besttoolbars.gdeslon

import com.fasterxml.jackson.databind.ObjectMapper
import net.besttoolbars.affiliate.core.AffiliateConnector
import net.besttoolbars.affiliate.core.HttpHandler
import net.besttoolbars.affiliate.core.provideXmlObjectMapper
import net.besttoolbars.gdeslon.response.GdeslonCouponsResponse
import org.apache.http.message.BasicNameValuePair
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

class Gdeslon @JvmOverloads constructor(
    httpHandler: HttpHandler = GdeslonHttpClient(),
    timeout: Long = 2,
    timeUnit: TimeUnit = TimeUnit.MINUTES,
    xmlMapper: ObjectMapper = provideXmlObjectMapper()
) : AffiliateConnector(httpHandler, xmlMapper, timeout, timeUnit) {
    override fun acceptHeader() = "application/xml"

    fun coupons(token: String): CompletableFuture<GdeslonCouponsResponse> {
        val query = listOf(
            BasicNameValuePair("search%5Bkind%5D", "6"),
            BasicNameValuePair("search%5Bcoupon_type%5D%5B%5D", "coupons"),
            BasicNameValuePair("search%5Bcoupon_type%5D%5B%5D", "promo"),
            BasicNameValuePair("api_token", token)
        )
        val request = buildRequest("$API_URL/api/coupons.xml", null, query)
        return makeRequest(request, GdeslonCouponsResponse::class)
    }

    companion object {
        const val API_URL = "https://www.gdeslon.ru"
    }
}
