package com.example.hotels.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.hotels.R
import com.example.hotels.data.model.Hotel
import com.example.hotels.data.network.ApiService.Companion.BASE_URL
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private var presenter: DetailContract.Presenter = DetailPresenter()

    private lateinit var progressBar: ProgressBar
    private lateinit var name: TextView
    private lateinit var address: TextView
    private lateinit var stars: TextView
    private lateinit var distance: TextView
    private lateinit var suites: TextView
    private lateinit var image: ImageView
    private lateinit var error: TextView

    private lateinit var addressImage: ImageView
    private lateinit var starsImage: ImageView
    private lateinit var distanceImage: ImageView
    private lateinit var suitesImage: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getIntExtra(getString(R.string.intent_key), -1)

        presenter.attachView(this)
        presenter.onViewCreated(id)

        initUI()
    }

    private fun initUI() {
        progressBar = findViewById(R.id.pb_detail)
        name = findViewById(R.id.tv_name)
        address = findViewById(R.id.tv_address)
        stars = findViewById(R.id.tv_stars)
        distance = findViewById(R.id.tv_distance)
        suites = findViewById(R.id.tv_suites)
        image = findViewById(R.id.iv_image)
        error = findViewById(R.id.tv_detail_error)

        addressImage = findViewById(R.id.iv_address)
        starsImage = findViewById(R.id.iv_stars)
        distanceImage = findViewById(R.id.iv_distance)
        suitesImage = findViewById(R.id.iv_suites)
    }

    override fun showHotel(hotel: Hotel) {
        progressBar.visibility = View.INVISIBLE
        addressImage.visibility = View.VISIBLE
        starsImage.visibility = View.VISIBLE
        distanceImage.visibility = View.VISIBLE
        suitesImage.visibility = View.VISIBLE

        name.text = hotel.name
        address.text = hotel.address
        stars.text = hotel.stars.toString()
        distance.text = String.format(getString(R.string.detail_distance), hotel.distance)
        suites.text = hotel.suites.toString()
        Picasso.get()
            .load(BASE_URL + hotel.image)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                    val bmp = Bitmap.createBitmap(bitmap, 1, 1, bitmap.width - 2, bitmap.height - 2)
                    image.setImageBitmap(bmp)
                }

                override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                }
            })
    }

    override fun showError() {
        progressBar.visibility = View.INVISIBLE
        error.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}