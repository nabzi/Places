package ir.nabzi.places.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.nabzi.places.model.Place
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun add(vararg Vehicle: Place)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun addList(list: List<Place>)

    @Query("SELECT * FROM place " )
    abstract fun getPlacesLivedata(): LiveData<List<Place>>

    @Query("SELECT * FROM place " )
    abstract fun getPlacesFLow(): Flow<List<Place>>
}