package ir.nabzi.places.ui.home

import androidx.lifecycle.*
import ir.nabzi.places.data.repository.PlaceRepository

class PlaceViewModel(placeRepository: PlaceRepository) : ViewModel() {
    val placeList = placeRepository.getPlaces(true, viewModelScope)
    val selectedPlaceId = MutableLiveData<String>()
    val place = selectedPlaceId.map { _id ->
        placeList.value?.data?.firstOrNull { it.id == _id }
    }
}
