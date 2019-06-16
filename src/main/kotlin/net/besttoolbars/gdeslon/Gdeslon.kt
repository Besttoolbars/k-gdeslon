package net.besttoolbars.gdeslon

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import net.besttoolbars.gdeslon.response.GdeslonCouponsResponse
import org.apache.http.Consts
import org.apache.http.client.utils.URLEncodedUtils
import org.apache.http.message.BasicNameValuePair
import java.net.URI
import java.net.http.HttpRequest
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

class Gdeslon @JvmOverloads constructor(
    private val httpHandler: GdeslonHttpHandler = GdeslonHttpClient(),
    private val timeout: Long = 2,
    private val timeUnit: TimeUnit = TimeUnit.MINUTES,
    private val xmlMapper: ObjectMapper = provideObjectMapper()
) {

    fun coupons(token: String): CompletableFuture<GdeslonCouponsResponse> {
        val query = listOf(
            BasicNameValuePair("search%5Bkind%5D", "6"),
            BasicNameValuePair("search%5Bcoupon_type%5D%5B%5D", "coupons"),
            BasicNameValuePair("search%5Bcoupon_type%5D%5B%5D", "promo"),
            BasicNameValuePair("api_token", token)
        )
        val request = buildRequest("$API_URL/api/coupons.xml", query)
        return httpHandler.executeRequest(request)
            .thenApply { xmlMapper.readValue(it, GdeslonCouponsResponse::class.java) }
            .orTimeout(timeout, timeUnit)
    }

    fun mapToQueryString(query: List<BasicNameValuePair>): String {
        return URLEncodedUtils.format(query, Consts.UTF_8)
    }

    fun buildRequest(url: String, query: List<BasicNameValuePair>?): HttpRequest {
        if (query.isNullOrEmpty()) {
            return buildRequest(url)
        }
        val queryString = mapToQueryString(query)
        return buildRequest("$url?$queryString")
    }

    fun buildRequest(url: String): HttpRequest {
        return HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build()
    }

    companion object {
        const val API_URL = "https://www.gdeslon.ru"

        fun provideObjectMapper(): ObjectMapper {
            val xmlMapper = XmlMapper().registerKotlinModule()
            val module = SimpleModule()
            module.addDeserializer(String::class.java, object : StdDeserializer<String>(String::class.java) {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): String? {
                    val result = StringDeserializer.instance.deserialize(p, ctxt)
                    return if (result.isNullOrBlank()) null else result.trim()
                }
            })
            xmlMapper.registerModule(module)
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return xmlMapper
        }
    }
}
