package com.sunnyweather.android.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Place

class PlaceAdapter(private val placesList:List<Place>) : RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false))
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