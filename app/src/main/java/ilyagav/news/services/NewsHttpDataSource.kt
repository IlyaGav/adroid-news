package ilyagav.news.services

import ilyagav.news.models.NewsResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsHttpDataSource {

    @GET("top-headlines")
    fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
    ): Call<NewsResponseModel?>
}