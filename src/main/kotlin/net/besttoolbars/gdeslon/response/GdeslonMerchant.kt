package net.besttoolbars.gdeslon.response

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class GdeslonMerchant(
    @field:JacksonXmlProperty(localName = "categories")
    @field:JacksonXmlElementWrapper(localName = "categories", useWrapping = true)
    val categories: List<GdeslonCategory>?,
    @field:JacksonXmlProperty(localName = "logo-file-name")
    val logoFileName: GdeslonLogoFileName?,
    val name: String?,
    val id: Int?
)
