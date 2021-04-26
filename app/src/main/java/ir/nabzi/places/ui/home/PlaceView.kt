package ir.nabzi.places.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import ir.nabzi.places.R
import ir.nabzi.places.model.Place

class PlaceView (layoutInflater: LayoutInflater, container: ViewGroup? ,val onClick : CALLBACK) {
    val view: View = layoutInflater.inflate(R.layout.item_place_layout, container, false)
    private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    private val cvPlace: CardView = view.findViewById(R.id.cvPlace)

    fun bind(place: Place) {
        tvTitle.text = place.title
        cvPlace.setOnClickListener {
            onClick(place.id)
        }

    }

}

