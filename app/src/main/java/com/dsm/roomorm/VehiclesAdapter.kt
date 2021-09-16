package com.dsm.roomorm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dsm.roomorm.databinding.ItemVehicleBinding
import com.dsm.roomorm.entities.Vehicle

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
        with(holder.binding) {
            tvTitle.text = list[position].name
            tvYear.text = list[position].year.toString()
            tvColor.text = list[position].color
        }
    }

    override fun getItemCount(): Int = list.size
}