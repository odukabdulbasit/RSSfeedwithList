package com.odukabdulbasit.rssfeedwithlist

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class RssFeed(
    @field:Element(name = "channel")
    var channel: RssChannel
)

/*data class RssChannel(
    @field:ElementList(inline = true, name = "item")
    var items: List<ResponseRssItem>
)*/

data class RssChannel(
    @field:ElementList(inline = true, name = "item")
    var items: List<ResponseRssItem>,

    @field:Element(name = "title")
    var title: String = "",

    @field:Element(name = "description")
    var description: String = "",

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "image", required = false)
var image: RssImage? = null,

    @field:Element(name = "generator", required = false)
    var generator: String? = null,

    @field:Element(name = "lastBuildDate", required = false)
    var lastBuildDate: String? = null,

    @field:Element(name = "copyright", required = false)
    var copyright: String? = null,

    @field:Element(name = "language", required = false)
    var language: String? = null,

    @field:Element(name = "ttl", required = false)
    var ttl: Int? = null // You can change the type to match the expected data type
)


data class RssImage(
    @field:Element(name = "url")
    var url: String = "",

    @field:Element(name = "title")
    var title: String = "",

    @field:Element(name = "link")
    var link: String = ""
)

data class ResponseRssItem(
    @field:Element(name = "title")
    var title: String = "",

    @field:Element(name = "description")
    var description: String = "",

    @field:Element(name = "link")
    var link: String = ""
)
