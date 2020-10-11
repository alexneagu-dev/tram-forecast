package ro.alexneagu.tramforecast.network

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.*
import ro.alexneagu.tramforecast.util.BASE_URL
import ro.alexneagu.tramforecast.model.StopInfo

interface RetrofitAPI {

    @GET("xml/get.ashx")
    fun getTramStops(
        @Query("action") action: String,
        @Query("stop") stop: String,
        @Query("encrypt") encrypt: Boolean
    ): Single<StopInfo>

    companion object {
        fun retrofitClient(): RetrofitAPI {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(RetrofitAPI::class.java)
        }
    }
}