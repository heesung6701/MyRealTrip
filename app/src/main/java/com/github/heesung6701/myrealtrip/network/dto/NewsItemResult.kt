package com.github.heesung6701.myrealtrip.network.dto

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class NewsItemResult {

    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "link", required = false)
    var link: String? = null

    @field:Element(name = "guid", required = false)
    var guid: String? = null

    @field:Element(name = "pubDate", required = false)
    var pubDate: String? = null

    @field:Element(name = "description", required = false)
    var description: String? = null

    @field:Element(name = "source", required = false)
    var source: String? = null

    override fun toString(): String {
        val col = listOf(title, link)
        return listOf("NEWS(",col.joinToString(","),")").joinToString("")
    }
}