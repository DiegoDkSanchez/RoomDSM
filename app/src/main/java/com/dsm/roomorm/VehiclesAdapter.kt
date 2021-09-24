package com.dsm.roomorm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.roomorm.databinding.ItemVehicleBinding
import com.dsm.roomorm.entities.Vehicle
import com.dsm.roomorm.repository.VehicleRepository
import kotlinx.coroutines.*

@Suppress("MemberVisibilityCanBePrivate")
class VehiclesAdapter(private val list: List<Vehicle>) :
    RecyclerView.Adapter<VehiclesAdapter.VehiclesViewHolder>() {

    class VehiclesViewHolder(val binding: ItemVehicleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
        val binding = ItemVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehiclesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            intent.putExtra("id_vehicle", list[position].id)
            it.context.startActivity(intent)
        }
        with(holder.binding) {
            tvTitle.text = "${list[position].brand?.name} ${list[position].name}"
            tvYear.text = list[position].year.toString()
            tvColor.text = list[position].color
        }
    }

    override fun getItemCount(): Int = list.size
}