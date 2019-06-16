package net.besttoolbars.gdeslon.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class GdeslonCoupon(
    @field:JacksonXmlProperty(localName = "merchant-id")
    val merchantId: Int?,

    @field:JacksonXmlProperty(localName = "start-at")
    val startAt: String?,

    @field:JacksonXmlProperty(localName = "finish-at")
    val finishAt: String?,

    @field:JacksonXmlProperty(localName = "url-with-code")
    val urlWithCode: String?,

    val kind: Int?,

    @field:JacksonXmlElementWrapper(localName = "coupon-categories", useWrapping = true)
    @field:JacksonXmlProperty(localName = "coupon-categories")
    val couponCategoriesIds: List<GdeslonCategory>?,

    val description: String?,

    val name: String?,

    val id: Int?,

    val code: String?,

    val url: String?,

    val instruction: String?
)

