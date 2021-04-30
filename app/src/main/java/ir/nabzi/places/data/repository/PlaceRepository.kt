package ir.nabzi.places.data.repository

import com.google.gson.Gson
import ir.nabzi.places.data.db.PlaceDao
import ir.nabzi.places.data.remote.ApiService
import ir.nabzi.places.model.NetworkCall
import ir.nabzi.places.model.Place
import ir.nabzi.places.model.Resource
import ir.nabzi.places.model.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.Response

interface PlaceRepository {
    fun getPlaces(
        shouldFetch: Boolean,
        coroutineScope: CoroutineScope
    ): StateFlow<Resource<List<Place>>?>
}

class PlaceRepositoryImpl(
    val placeDao: PlaceDao,
    val apiServices: ApiService
) : PlaceRepository {
    override fun getPlaces(
        shouldFetch: Boolean,
        coroutineScope: CoroutineScope
    ): StateFlow<Resource<List<Place>>?> {
        var stateFlow: MutableStateFlow<Resource<List<Place>>?> =
            MutableStateFlow(Resource.loading(null))
        coroutineScope.launch(Dispatchers.IO) {
            stateFlow.emit(if (shouldFetch) {
                val resource = pullPlacesFromServer()

                if (resource.status == Status.ERROR) {
                    Resource.error<List<Place>>(
                        resource.message ?: "error loading from server", placeDao.getPlaces()
                    )
                } else {
                    resource.data?.let {
                        placeDao.addList(it)
                    }
                    Resource.success(placeDao.getPlaces())
                }

            } else {
                Resource.success(placeDao.getPlaces())
            }
            )
        }
        return stateFlow
    }

    private suspend fun pullPlacesFromServer(): Resource<List<Place>> {
        var result: Resource<List<Place>> = object : NetworkCall<List<Place>>() {
            override suspend fun createCall(): Response<List<Place>> {
                return apiServices.getPlaceList()
            }
        }.fetch()
        return result
    }
}