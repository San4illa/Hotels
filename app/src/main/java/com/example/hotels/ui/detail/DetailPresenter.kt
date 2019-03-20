package com.example.hotels.ui.detail

import com.example.hotels.data.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenter : DetailContract.Presenter {

    private var view: DetailContract.View? = null
    private var repository: Repository = Repository()

    override fun attachView(view: DetailContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onViewCreated(id: Int) {
        repository.getHotel(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { hotel ->
                    view?.showHotel(hotel)
                },
                {
                    view?.showError()
                }
            )
    }
}
