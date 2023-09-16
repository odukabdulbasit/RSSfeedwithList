package com.odukabdulbasit.rssfeedwithlist

import retrofit2.Response
import retrofit2.http.GET

interface RssService {
    @GET("news/rss.xml")
    suspend fun getRssFeed(): Response<RssFeed>
}

