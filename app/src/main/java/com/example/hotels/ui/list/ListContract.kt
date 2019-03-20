package com.example.hotels.ui.list

import com.example.hotels.data.model.Hotel

interface ListContract {

    interface View {
        fun showHotels(hotels: List<Hotel>)
        fun showError()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onViewCreated()
        fun onSortByDistanceButtonClicked()
        fun onSortByRoomButtonClicked()
    }
}