package ir.nabzi.places.data.repository

import com.google.gson.Gson
import ir.nabzi.places.data.db.PlaceDao
import ir.nabzi.places.data.remote.ApiService

interface PlaceRepository {
}

class PlaceRepositoryImpl(val placeDao: PlaceDao,
                          val apiServices: ApiService,
                          val gson: Gson
) : PlaceRepository {
     fun getPlaces(){

     }
}