package com.example.fragmentexercise.botnav

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentexercise.R
import com.example.fragmentexercise.adapter.CategoryAdapter
import com.example.fragmentexercise.adapter.ServiceAdapter
import com.example.fragmentexercise.api.ApiClient
import com.example.fragmentexercise.data.Category
import com.example.fragmentexercise.data.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var kategoriRv: RecyclerView
    private lateinit var serviceRv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        kategoriRv = view.findViewById(R.id.kategoriRv)
        kategoriRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        serviceRv = view.findViewById(R.id.teknisiRv)
        serviceRv.layoutManager = GridLayoutManager(requireContext(), 2)

        loadCategories()
        loadServices() // <-- load service grid

        return view
    }

    private fun loadCategories() {
        val sharedPref = requireContext().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        val token = sharedPref.getString("TOKEN", null)

        if (token.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Silakan login dulu", Toast.LENGTH_SHORT).show()
            return
        }

        ApiClient.instance.getCategories("Bearer $token").enqueue(object : Callback<List<Category>> {
            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                if (response.isSuccessful) {
                    val categories = response.body() ?: emptyList()
                    kategoriRv.adapter = CategoryAdapter(categories)
                } else {
                    Toast.makeText(requireContext(), "Gagal: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadServices() {
        val sharedPref = requireContext().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        val token = sharedPref.getString("TOKEN", null)

        if (token.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Silakan login dulu", Toast.LENGTH_SHORT).show()
            return
        }

        ApiClient.instance.getServices("Bearer $token").enqueue(object : Callback<List<Service>> {
            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
                if (response.isSuccessful) {
                    val services = response.body() ?: emptyList()
                    serviceRv.adapter = ServiceAdapter(services)
                } else {
                    Toast.makeText(requireContext(), "Gagal load service: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error load service: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}