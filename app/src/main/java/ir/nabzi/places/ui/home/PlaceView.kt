package ir.nabzi.places.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ir.nabzi.places.R
import ir.nabzi.places.model.Place

class PlaceView (layoutInflater: LayoutInflater, container: ViewGroup?) {
    val view: View = layoutInflater.inflate(R.layout.item_place_layout, container, false)
    private val tvTitle: TextView = view.findViewById(R.id.tvTitle)

    fun bind(place: Place) {
        tvTitle.text = place.title
    }

}

