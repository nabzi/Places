package ir.nabzi.places.ir.nabzi.places.data.repository

import ir.nabzi.places.model.Place
import ir.nabzi.places.model.Resource
import ir.nabzi.places.model.Status

abstract class RemoteResource<ResultType> {
    suspend fun get(shouldFetch : Boolean): Resource<ResultType>{
        return if (shouldFetch) {
            val resource = pullFromServer()

            if (resource.status == Status.ERROR) {
                Resource.error<ResultType>(
                    resource.message ?: "error loading from server", getFromDB()
                )
            } else {
                resource.data?.let {
                    updateDB(it)
                }
                Resource.success(getFromDB())
            }

        } else {
            Resource.success(getFromDB())
        }
    }

    abstract suspend fun updateDB(result : ResultType)

    abstract fun getFromDB(): ResultType

    abstract suspend fun pullFromServer(): Resource<ResultType>
}