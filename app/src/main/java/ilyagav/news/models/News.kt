package ilyagav.news.models

import com.google.gson.annotations.SerializedName

class News(val title: String, val description: String, val img: String, val url: String) : java.io.Serializable {

    val isValid: Boolean
    get() = title.isNotEmpty() && description.isNotEmpty() && img.isNotEmpty() && url.isNotEmpty();
}

class NewsModel {
    @SerializedName("title")
    val title: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("urlToImage")
    val img: String? = null

    @SerializedName("url")
    val url: String? = null
}


class NewsResponseModel {
    @SerializedName("articles")
    val articles: List<NewsModel>? = null
}