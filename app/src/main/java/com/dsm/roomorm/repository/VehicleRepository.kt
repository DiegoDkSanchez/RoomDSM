package com.dsm.roomorm.repository

import android.content.Context
import com.dsm.roomorm.dao.BrandDao
import com.dsm.roomorm.dao.VehicleDao
import com.dsm.roomorm.database.VehicleRoomDatabase
import com.dsm.roomorm.entities.Brand
import com.dsm.roomorm.entities.Vehicle
import kotlinx.coroutines.flow.Flow

class VehicleRepository(private val vehicleDao: VehicleDao, private val brandDao: BrandDao) {

    companion object {
        private var INSTANCE : VehicleRepository? = null

        fun getRepository(context: Context) : VehicleRepository {
            return INSTANCE ?: synchronized(this) {
                val database = VehicleRoomDatabase.getDatabase(context)
                val instance = VehicleRepository(database.vehicleDao(), database.brandDao())
                INSTANCE = instance
                instance
            }

        }
    }

    // Vehicle Dao
    val allVehicles: Flow<List<Vehicle>> = vehicleDao.getAlphabetizedVehicles()
    suspend fun insert(vehicle: Vehicle) {
        vehicleDao.insert(vehicle)
    }
    suspend fun deleteAll() {
        vehicleDao.deleteAll()
    }

    // Brand Dao
    val brands: Flow<List<Brand>> = brandDao.getBrands()
    suspend fun insertBrand(brand: Brand) {
        brandDao.insert(brand)
    }
    suspend fun deleteBrand(id: Int) {
        brandDao.deleteBrand(id)
    }
}