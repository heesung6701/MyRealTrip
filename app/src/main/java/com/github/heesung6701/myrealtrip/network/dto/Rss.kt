package com.github.heesung6701.myrealtrip.network.dto

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import kotlin.properties.Delegates

@Root(name = "rss", strict = false)
class Rss {

    @field:Element(name = "channel")
    var channel: Channel? = null

    class Channel {
        @field:Element(name = "generator", required = false)
        var generator: String? = null

        @field:Element(name = "title", required = false)
        var title: String? = null

        @field:Element(name = "link", required = false)
        var link: String? = null

        @field:Element(name = "language", required = false)
        var language: String? = null

        @field:Element(name = "webMaster", required = false)
        var webMaster: String? = null

        @field:Element(name = "copyright", required = false)
        var copyright: String? = null

        @field:Element(name = "lastBuildDate", required = false)
        var lastBuildDate: String? = null

        @field:Element(name = "description", required = false)
        var description: String? = null

        @field:ElementList(inline = true, required = false)
        var news: List<NewsItemResult>? = null

        override fun toString(): String {
            val col = listOf(generator, title,link,language, webMaster, copyright, lastBuildDate, description,news)
            return listOf("CHANNEL(", col.joinToString(","), ")").joinToString("")
        }
    }

    override fun toString(): String {
        val col = listOf(channel)
        return col.joinToString(",")
    }
}