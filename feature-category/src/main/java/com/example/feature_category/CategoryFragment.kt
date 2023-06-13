package com.example.feature_category

import android.Manifest
import android.content.pm.PackageManager
import androidx.navigation.fragment.findNavController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.dto.Category
import com.example.core.listener.OnInteractionListener
import com.example.core.utils.StringArg
import com.example.feature_category.databinding.FragmentCategoryBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: CategoriesViewModel by activityViewModels()

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
        val binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val gson = Gson()

        val adapter = CategoryAdapter(
            object : OnInteractionListener<Category> {
                override fun onSelect(item: Category) {
                    findNavController().navigate(
                        R.id.action_categoryFragment_to_menuFragment,
                        Bundle().apply {
                            textArg = gson.toJson(item)
                        }
                    )
                }
            }
        )

        binding.apply {
            viewModel.location.observe(viewLifecycleOwner) {
                location.text = "${it.latitude}, ${it.longitude}"
            }
            date.text = sdf.format(calendar.time)
            list.adapter = adapter
            list.layoutManager = LinearLayoutManager(context)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
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