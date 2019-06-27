package net.besttoolbars.gdeslon.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

data class GdeslonCategory(
    val id: Int,
    val name: String?
)

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

@JacksonXmlRootElement(localName = "gdeslon-coupons")
data class GdeslonCouponsResponse(
    @field:JacksonXmlElementWrapper(localName = "coupon-categories", useWrapping = true)
    @field:JacksonXmlProperty(localName = "coupon-categories")
    val couponCategories: List<GdeslonCategory>,

    @field:JacksonXmlElementWrapper(localName = "categories", useWrapping = true)
    @field:JacksonXmlProperty(localName = "categories")
    val categories: List<GdeslonCategory>,

    @field:JacksonXmlElementWrapper(localName = "merchants", useWrapping = true)
    @field:JacksonXmlProperty(localName = "merchants")
    val merchants: List<GdeslonMerchant>,

    @field:JacksonXmlElementWrapper(localName = "kinds", useWrapping = true)
    @field:JacksonXmlProperty(localName = "kinds")
    val kinds: List<GdeslonKind>,

    @field:JacksonXmlElementWrapper(localName = "coupons", useWrapping = true)
    @field:JacksonXmlProperty(localName = "coupons")
    val coupons: List<GdeslonCoupon>
)

data class GdeslonKind(
    val name: String?,
    val id: Int?
)

data class GdeslonLogoFileName(
    val big: String?,
    val regular: String?,
    val small: String?
)

data class GdeslonMerchant(
    @field:JacksonXmlProperty(localName = "categories")
    @field:JacksonXmlElementWrapper(localName = "categories", useWrapping = true)
    val categories: List<GdeslonCategory>?,

    @field:JacksonXmlProperty(localName = "logo-file-name")
    val logoFileName: GdeslonLogoFileName?,

    val name: String?,

    val id: Int?
)
