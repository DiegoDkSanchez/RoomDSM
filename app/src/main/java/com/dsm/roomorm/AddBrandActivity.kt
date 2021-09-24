package com.dsm.roomorm

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import com.dsm.roomorm.databinding.ActivityAddBrandBinding
import com.dsm.roomorm.entities.Brand
import com.dsm.roomorm.repository.VehicleRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class AddBrandActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBrandBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBrandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addListeners()
    }

    private fun addListeners() {
        val repository = VehicleRepository.getRepository(this)

        binding.btnAdd.setOnClickListener {

            hideKeyboard()
            binding.apply {
                if (etName.text.isBlank() || etCountry.text.isBlank()) {
                    Snackbar.make(this.root, "Some fields are empty", Snackbar.LENGTH_SHORT).show()
                } else {

                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            repository.insertBrand(
                                Brand(
                                    name = etName.text.toString(),
                                    country = etCountry.text.toString(),
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