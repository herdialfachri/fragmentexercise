package com.example.fragmentexercise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentexercise.R
import com.example.fragmentexercise.data.Service
import java.text.NumberFormat
import java.util.Locale

class ServiceAdapter(private val services: List<Service>) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    inner class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceNameTv: TextView = itemView.findViewById(R.id.serviceNameTv)
        val serviceOwnerTv: TextView = itemView.findViewById(R.id.serviceOwnerTv)
        val servicePriceTv: TextView = itemView.findViewById(R.id.servicePriceTv)
        val serviceRatingTv: TextView = itemView.findViewById(R.id.serviceRatingTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_service, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        holder.serviceNameTv.text = service.serviceName
        holder.serviceOwnerTv.text = service.user.name
        holder.serviceRatingTv.text = service.averageRating

        // Format harga ke Rupiah Indonesia
        val priceDouble = service.price.toDoubleOrNull() ?: 0.0
        val formattedPrice = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            .format(priceDouble)
            .replace(",00", "")

        holder.servicePriceTv.text = formattedPrice
    }

    override fun getItemCount(): Int = services.size
}