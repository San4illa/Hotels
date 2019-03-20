package com.example.hotels.data.model

data class HotelResponse(
    var id: Int, var name: String, var address: String, var stars: Double,
    var distance: Double, var image: String?, var suites: String,
    var lat: Double?, var lng: Double?
)
