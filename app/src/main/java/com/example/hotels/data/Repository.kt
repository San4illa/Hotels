package com.example.hotels.data

import com.example.hotels.data.mapper.HotelMapper
import com.example.hotels.data.model.Hotel
import com.example.hotels.data.network.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository {

    private var apiService = ApiService.create()
    private var mapper: HotelMapper = HotelMapper()

    fun getHotels(): Single<List<Hotel>> {
        return apiService.getHotels()
            .map { hotels -> mapper.responseDataToEntity(hotels) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getHotel(id: Int): Single<Hotel> {
        return apiService.getHotel(id)
            .map { hotel -> mapper.responseDataToEntity(hotel) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}