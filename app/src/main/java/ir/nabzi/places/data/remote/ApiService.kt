package ir.nabzi.places.data.remote

import ir.nabzi.places.model.Place
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/v1/places")
    suspend fun getVehicleList() : Response<List<Place>>

}