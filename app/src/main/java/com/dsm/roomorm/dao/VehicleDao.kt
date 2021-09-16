package com.dsm.roomorm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.roomorm.entities.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicle_table ORDER BY name ASC")
    fun getAlphabetizedVehicles(): Flow<List<Vehicle>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vehicle: Vehicle)

    @Query("DELETE FROM vehicle_table")
    suspend fun deleteAll()

}