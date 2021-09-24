package com.dsm.roomorm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.roomorm.entities.Brand
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandDao {

    @Query("SELECT * FROM brand_table")
    fun getBrands(): Flow<List<Brand>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(brand: Brand)

    @Query("DELETE FROM VEHICLE_TABLE WHERE brandId = :id")
    suspend fun deleteBrand(id: Int)

}