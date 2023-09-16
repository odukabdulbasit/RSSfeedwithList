package com.odukabdulbasit.rssfeedwithlist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RssAdapter
    private lateinit var rssService: RssService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RssAdapter()
        recyclerView.adapter = adapter

        // Initialize Retrofit and create a service instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://feeds.bbci.co.uk/") // Replace with your RSS feed URL
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()

        rssService = retrofit.create(RssService::class.java)

        // Fetch and display the RSS feed
        fetchData()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = rssService.getRssFeed()
                Log.e("RSS", "Response: $response")
                if (response.isSuccessful) {
                    val rssFeed = response.body()
                    val items = rssFeed?.channel?.items ?: emptyList()
                    //val items = rssFeed?.items ?: emptyList()
                    withContext(Dispatchers.Main) {
                        adapter.setData(items as List<RssItem>)
                    }
                } else {
                    // Handle error
                    Log.e("RSS", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                // Handle exception
                Log.e("RSS", "Exception: ${e.message}")
            }
        }
    }
}
