package com.example.hotels.ui.list

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.R
import com.example.hotels.data.model.Hotel
import com.example.hotels.ui.detail.DetailActivity

internal class ListAdapter(var hotels: List<Hotel>) :
    RecyclerView.Adapter<ListAdapter.ListAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.hotel_list_item, parent, false)
        return ListAdapterViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ListAdapterViewHolder, position: Int) {
        val hotel = hotels[position]

        viewHolder.bind(hotel)
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    inner class ListAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var name: TextView? = null
        private var address: TextView? = null
        private var rooms: TextView? = null
        private var distance: TextView? = null

        init {
            name = itemView.findViewById(R.id.tv_item_name)
            address = itemView.findViewById(R.id.tv_item_address)
            rooms = itemView.findViewById(R.id.tv_item_rooms)
            distance = itemView.findViewById(R.id.tv_item_distance)
        }

        fun bind(hotel: Hotel) {
            val context: Context = itemView.context

            name?.text = hotel.name
            address?.text = hotel.address
            rooms?.text =
                String.format(context.getString(R.string.item_rooms), hotel.suites)
            distance?.text =
                String.format(context.getString(R.string.item_distance), hotel.distance)

            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(context.getString(R.string.intent_key), hotel.id)
                context.startActivity(intent)
            }
        }
    }
}
