package ir.nabzi.places.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import ir.nabzi.places.R
import ir.nabzi.places.databinding.ItemPlaceLayoutBindingImpl
import ir.nabzi.places.model.Place

class PlaceView(layoutInflater: LayoutInflater, container: ViewGroup?, val onClick: CALLBACK) {
    val binding : ItemPlaceLayoutBindingImpl = DataBindingUtil.inflate(
        layoutInflater,
        R.layout.item_place_layout, container,false
    )
    fun bind(place: Place) {
        with(binding) {
            placeitem = place
            cvPlace.setOnClickListener {
                onClick(place.id)
            }
            executePendingBindings()
        }

    }
}

