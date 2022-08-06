package com.example.countries.retrofit

import com.example.countries.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
class RetroInstance {

    companion object{

    val BASE_URL = "https://wft-geo-db.p.rapidapi.com/"
    private var retrofit: Retrofit? = null
    private val REQUEST_TIMEOUT = 60
    private var logging = HttpLoggingInterceptor()

    private fun getOkHttpLogClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)


        //Adding http logging
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        httpClient.addInterceptor(logging)

        //Adding headers
        httpClient.addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key","b77c2e5355msh961888981644db6p1e2dabjsn59c55a90878d")
                .addHeader("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com")
                .build()
            chain.proceed(newRequest)
        }

        return httpClient.build()
    }
        fun getRetroInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpLogClient())
                    .build()
            }
            return retrofit
    }
    }
}