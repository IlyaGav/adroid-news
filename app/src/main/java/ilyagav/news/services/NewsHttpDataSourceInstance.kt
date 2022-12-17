package ilyagav.news.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsHttpDataSourceInstance {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://newsapi.org/v2/"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                val mHttpLoggingInterceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)

                val mOkHttpClient = OkHttpClient
                    .Builder()
                    .addInterceptor(mHttpLoggingInterceptor)
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request().newBuilder().addHeader("client", "web")
                                .addHeader("client-country", "RU")
                                .build()
                        )
                    }
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build()
            }
            return retrofit
        }
}