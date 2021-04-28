package ir.nabzi.places.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.nabzi.places.data.repository.PlaceRepository

class PlaceViewModel (placeRepository: PlaceRepository) : ViewModel() {
    val placeList = placeRepository.getPlaces(true , viewModelScope)
}