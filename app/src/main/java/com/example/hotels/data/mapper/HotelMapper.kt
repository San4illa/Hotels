package com.example.hotels.data.mapper

import com.example.hotels.data.model.Hotel
import com.example.hotels.data.model.HotelResponse

class HotelMapper {

    fun responseDataToEntity(hotelResponse: HotelResponse): Hotel {
        return Hotel(
            hotelResponse.id,
            hotelResponse.name,
            hotelResponse.address,
            hotelResponse.stars,
            hotelResponse.distance,
            hotelResponse.image,
            countSuites(hotelResponse.suites),
            hotelResponse.lat,
            hotelResponse.lng
        )
    }

    fun responseDataToEntity(hotelResponses: List<HotelResponse>): List<Hotel> {
        val hotels: ArrayList<Hotel> = ArrayList()
        for (hotelResponse in hotelResponses) {
            hotels.add(responseDataToEntity(hotelResponse))
        }
        return hotels
    }

    private fun countSuites(suites: String): Int {
        if (suites.isEmpty())
            return 0

        var a = 1
        for (s in suites) {
            if (s.toString() == " ")
                a++
        }
        return a
    }
}