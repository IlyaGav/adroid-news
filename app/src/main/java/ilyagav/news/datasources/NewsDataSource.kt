package ilyagav.news.datasources

import ilyagav.news.models.News
import ilyagav.news.models.NewsResponseModel
import ilyagav.news.services.NewsHttpDataSource
import ilyagav.news.services.NewsHttpDataSourceInstance.retrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDataSource {

    fun loadItems(country: String, callback: ((List<News>) -> Unit)) {

        val service = retrofitInstance!!.create(
            NewsHttpDataSource::class.java
        )

        val apiKey= "2f746bfe62bb4aa98b61d0399d102bcf"

        val call: Call<NewsResponseModel?> = service.getNews(country, apiKey)

        call.enqueue(object : Callback<NewsResponseModel?> {
            override fun onResponse(
                call: Call<NewsResponseModel?>,
                response: Response<NewsResponseModel?>,
            ) {
                val body = response.body()
                val res = body?.articles ?: listOf()
                val data = res
                    .map { News(it.title ?: "", it.description ?: "", it.img ?: "", it.url ?: "") }
                    .filter { news -> news.isValid }

                callback(data)
            }

            override fun onFailure(call: Call<NewsResponseModel?>, t: Throwable) {

            }
        })
    }
}