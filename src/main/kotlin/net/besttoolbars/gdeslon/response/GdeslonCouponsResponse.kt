package net.besttoolbars.gdeslon.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

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
