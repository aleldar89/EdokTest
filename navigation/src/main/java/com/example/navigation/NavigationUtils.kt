package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import java.io.Serializable

private const val NAVIGATION_DATA = "navigation data"

fun Fragment.navigate(actionId: Int, hostId: Int? = null, args: Bundle? = null) {
    val navController = if (hostId == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), hostId)
    }

    navController.navigate(actionId, args)
}
