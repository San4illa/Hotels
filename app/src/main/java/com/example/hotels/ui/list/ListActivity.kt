package com.example.hotels.ui.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.R
import com.example.hotels.data.model.Hotel

class ListActivity : AppCompatActivity(), ListContract.View {

    private var presenter: ListContract.Presenter = ListPresenter()

    private lateinit var progressBar: ProgressBar
    private lateinit var error: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListAdapter

    private var hotels: ArrayList<Hotel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        presenter.attachView(this)
        presenter.onViewCreated()

        initUI()
    }

    private fun initUI() {
        progressBar = findViewById(R.id.pb_list)
        error = findViewById(R.id.tv_list_error)

        recyclerView = findViewById(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ListAdapter(hotels)
        recyclerView.adapter = adapter
    }

    override fun showHotels(hotels: List<Hotel>) {
        progressBar.visibility = View.INVISIBLE
        this.hotels.clear()
        this.hotels.addAll(hotels)
        adapter.notifyDataSetChanged()
    }

    override fun showError() {
        progressBar.visibility = View.INVISIBLE
        error.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort_by_distance -> {
                presenter.onSortByDistanceButtonClicked()
            }
            R.id.action_sort_by_rooms -> {
                presenter.onSortByRoomButtonClicked()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
