package ir.nabzi.places.ui.home

import androidx.lifecycle.*
import ir.nabzi.places.data.repository.PlaceRepository
import ir.nabzi.places.model.Place
import ir.nabzi.places.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlaceViewModel(placeRepository: PlaceRepository) : ViewModel() {
    val placeList : MutableStateFlow<Resource<List<Place>>?> = MutableStateFlow(Resource.loading(null))
    init{
        viewModelScope.launch(Dispatchers.IO) {
            placeList.value =  placeRepository.getPlaces(true).value
        }
    }
    val selectedPlaceId = MutableLiveData<String>()
    val place = selectedPlaceId.map { _id ->
        placeList.value?.data?.firstOrNull { it.id == _id }
    }
}
