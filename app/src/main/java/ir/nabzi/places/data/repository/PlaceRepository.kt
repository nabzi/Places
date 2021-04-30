package ir.nabzi.places.data.repository

import com.google.gson.Gson
import ir.nabzi.places.data.db.PlaceDao
import ir.nabzi.places.data.remote.ApiService
import ir.nabzi.places.ir.nabzi.places.data.repository.RemoteResource
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
    suspend fun getPlaces(
        shouldFetch: Boolean
    ): StateFlow<Resource<List<Place>>?>
}

class PlaceRepositoryImpl(
    val placeDao: PlaceDao,
    val apiServices: ApiService
) : PlaceRepository {

    override suspend fun getPlaces(shouldFetch: Boolean): StateFlow<Resource<List<Place>>?> {
        var stateFlow: MutableStateFlow<Resource<List<Place>>?> =
            MutableStateFlow(Resource.loading(null))
        stateFlow.emit(
            object : RemoteResource<List<Place>>() {
                override suspend fun updateDB(result: List<Place>) {
                    placeDao.addList(result)
                }

                override fun getFromDB(): List<Place> {
                    return placeDao.getPlaces()
                }

                override suspend fun pullFromServer(): Resource<List<Place>> {
                    return object : NetworkCall<List<Place>>() {
                        override suspend fun createCall(): Response<List<Place>> {
                            return apiServices.getPlaceList()
                        }
                    }.fetch()
                }
            }.get(true)
        )
        return stateFlow
    }

}