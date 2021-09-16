package com.dsm.roomorm

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import com.dsm.roomorm.databinding.ActivityAddVehicleBinding
import com.dsm.roomorm.entities.Vehicle
import com.dsm.roomorm.repository.VehicleRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddVehicleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addListener()
    }

    private fun addListener() {
        
        val repository = VehicleRepository.getRepository(this)

        binding.btnAdd.setOnClickListener {
            hideKeyboard()
            with(binding) {
                if (etName.text.isBlank() || etYear.text.isBlank() || etColor.text.isBlank()) {
                    Snackbar.make(this.root, "Some fields are empty", Snackbar.LENGTH_SHORT).show()
                } else {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            repository.insert(
                                Vehicle(
                                    name = etName.text.toString(),
                                    year = etYear.text.toString().toInt(),
                                    color = etColor.text.toString()
                                )
                            )
                        }
                        onBackPressed()
                    }
                }
            }
        }
    }

    private fun hideKeyboard() {
        val manager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

}