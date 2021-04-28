package ir.nabzi.places

import ir.nabzi.places.data.remote.*
import ir.nabzi.places.model.NetworkCall
import ir.nabzi.places.model.Place
import ir.nabzi.places.model.Resource
import ir.nabzi.places.model.Status
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ApiServiceTest {
    lateinit var apiServices : ApiService
    @Before
    fun setup(){
       apiServices=createService(createRetrofit(createHttpClient(), BASE_URL))
    }

    @Test
    fun getPlacesList_success() = runBlocking {
        var result: Resource<List<Place>> = object  : NetworkCall<List<Place>>(){
            override suspend fun createCall(): Response<List<Place>> {
                return apiServices.getPlaceList()
            }
        }.fetch()
        assert(result.status == Status.SUCCESS)
    }
}