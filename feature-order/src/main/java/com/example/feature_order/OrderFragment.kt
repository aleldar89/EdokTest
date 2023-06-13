package com.example.feature_order

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.dto.Dish
import com.example.core.listener.OnInteractionListener
import com.example.feature_order.databinding.FragmentOrderBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()

    private val calendar = Calendar.getInstance()
    private val datePattern = "d MMMM, yyyy"
    private val sdf = SimpleDateFormat(datePattern, Locale.getDefault())

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        getCurrentLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOrderBinding.inflate(inflater, container, false)

        val orderAdapter = OrderAdapter(
            object : OnInteractionListener<Dish> {
                override fun onAdd(item: Dish) {
                    viewModel.orderPlus(item)
                }

                override fun onRemove(item: Dish) {
                    viewModel.orderMinus(item)
                }
            }
        )

        binding.apply {
            viewModel.location.observe(viewLifecycleOwner) {
                location.text = "${it.latitude}, ${it.longitude}"
            }
            date.text = sdf.format(calendar.time)
            list.adapter = orderAdapter
            list.layoutManager = LinearLayoutManager(context)
        }

        viewModel.orderData.observe(viewLifecycleOwner) {
            if(it.isNullOrEmpty()) {
                viewModel.cleanSum()
            }
            orderAdapter.submitList(it)
        }

        viewModel.sum.observe(viewLifecycleOwner) { sum ->
            binding.apply {
                if (sum > 0) {
                    orderButton.text = "Оплатить $sum Р"
                    orderButton.isVisible = true
                } else {
                    orderButton.isGone = true
                }
            }
        }

        return binding.root
    }

    private fun getCurrentLocation() {
        lifecycle.coroutineScope.launchWhenCreated {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) -> {
                    fusedLocationClient.lastLocation.addOnSuccessListener { lastLocation ->
                        if (lastLocation != null) {
                            viewModel.saveLocation(lastLocation)
                        } else {
                            return@addOnSuccessListener
                        }
                    }
                }
                else -> {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        }
    }

}