package com.sunnyweather.android.ui.place

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.R
import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.ui.weather.WeatherActivity

class PlaceAdapter(private val placesList:List<Place>,private val fragment:PlaceFragment) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder=ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false))
        holder.itemView.setOnClickListener {
            val position=holder.adapterPosition
            val place=placesList[position]
            val intent=Intent(parent.context,WeatherActivity::class.java).apply {
                putExtra("location_lng",place.location.lng)
                putExtra("location_lat",place.location.lat)
                putExtra("place_name",place.name)
            }
            fragment.viewModel.savePlace(place)
            fragment.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount()=placesList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place=placesList[position]
        holder.placeName.text=place.name
        holder.placeAddress.text=place.address
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val placeName:TextView=view.findViewById(R.id.placeName)
        val placeAddress:TextView=view.findViewById(R.id.placeAddress)
    }
}