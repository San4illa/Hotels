package com.example.hotels.ui.list

import com.example.hotels.data.Repository
import com.example.hotels.data.model.Hotel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListPresenter : ListContract.Presenter {

    private var view: ListContract.View? = null
    private var repository: Repository = Repository()

    private var hotels: ArrayList<Hotel> = ArrayList()

    override fun attachView(view: ListContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onViewCreated() {
        repository.getHotels()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { hotels ->
                    this.hotels.addAll(hotels)
                    view?.showHotels(hotels)
                },
                {
                    view?.showError()

                }
            )
    }

    override fun onSortByDistanceButtonClicked() {
        val sortedHotels = hotels.sortedWith(compareBy(Hotel::distance))
        hotels.clear()
        hotels.addAll(sortedHotels)
        view?.showHotels(hotels)
    }

    override fun onSortByRoomButtonClicked() {
        val sortedHotels = hotels.sortedWith(compareBy(Hotel::suites)).reversed()
        hotels.clear()
        hotels.addAll(sortedHotels)
        view?.showHotels(hotels)
    }
}