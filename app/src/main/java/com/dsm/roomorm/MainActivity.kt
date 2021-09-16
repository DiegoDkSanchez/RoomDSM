package com.dsm.roomorm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.dsm.roomorm.database.VehicleRoomDatabase
import com.dsm.roomorm.databinding.ActivityMainBinding
import com.dsm.roomorm.entities.Vehicle
import com.dsm.roomorm.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var repository : VehicleRepository
    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = VehicleRepository.getRepository(this)

        lifecycleScope.launch(Dispatchers.IO) {
            repository.allVehicles.collect {
                it.forEach { vehicle ->
                    println(vehicle)
                }
            }
        }

        binding.btnInsert.setOnClickListener {
            lifecycleScope.launch {
                addVehicle()
            }
        }
    }

    private suspend fun addVehicle() {
        repository.insert(Vehicle(name = binding.etName.text.toString(), color = "asdas", year = 1998))
    }
}