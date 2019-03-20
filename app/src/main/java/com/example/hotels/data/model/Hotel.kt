package com.example.hotels.data.model

data class Hotel(
    var id: Int, var name: String, var address: String, var stars: Double,
    var distance: Double, var image: String?, var suites: Int,
    var lat: Double?, var lng: Double?
)

