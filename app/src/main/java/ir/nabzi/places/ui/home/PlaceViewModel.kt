package ir.nabzi.places.ui.home

import androidx.lifecycle.*
import ir.nabzi.places.data.repository.PlaceRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull

class PlaceViewModel(placeRepository: PlaceRepository) : ViewModel() {
    val placeList = placeRepository.getPlaces(true, viewModelScope)


    val id = MutableLiveData<String>()
    val place = id.map { _id ->
        placeList.value?.data?.firstOrNull { it.id == _id }
    }
}
