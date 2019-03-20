package com.example.hotels.ui.detail

import com.example.hotels.data.model.Hotel

interface DetailContract {

    interface View {
        fun showHotel(hotel: Hotel)
        fun showError()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onViewCreated(id: Int)
    }
}