package com.example.hotels.data.network

import com.example.hotels.data.model.HotelResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("test.json")
    fun getHotels(): Single<List<HotelResponse>>

    @GET("{id}.json")
    fun getHotel(@Path("id") id: Int): Single<HotelResponse>

    companion object {
        const val BASE_URL =
            "https://raw.githubusercontent.com/San4illa/Hotels/master/api/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}
