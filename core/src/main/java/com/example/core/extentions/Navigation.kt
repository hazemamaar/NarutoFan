package com.example.core.extentions

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.core.R

fun LifecycleOwner.navigateSafe(
    directions: NavDirections,
    navOptions: NavOptions? = null,
    container: Int,
) {
    val navController: NavController?
    val mView: View?
    if (this is Fragment) {
        navController = findNavController()
        mView = view
    } else {
        val activity = this as Activity
        navController = activity.findNavController(container)
        mView = currentFocus
    }
    if (canNavigate(navController, mView)) navController.navigate(directions, navOptions)

}

fun LifecycleOwner.popBack(container: Int) {
    val navController: NavController?
    val mView: View?
    if (this is Fragment) {
        navController = findNavController()
        mView = view
    } else {
        val activity = this as Activity
        navController = activity.findNavController(container)
        mView = currentFocus
    }
    if (canNavigate(navController, mView)) navController.popBackStack()

}

fun LifecycleOwner.navigateSafe(
    @IdRes navFragmentRes: Int,
    bundle: Bundle? = null,
    navOptions: NavOptions? = null,
    container: Int,
) {
    val navController: NavController?
    val mView: View?
    if (this is Fragment) {
        navController = findNavController()
        mView = view
    } else {
        val activity = this as Activity
        navController = activity.findNavController(container)
        mView = currentFocus
    }
    if (canNavigate(navController, mView)) {
        val currentDest = navController.currentDestination?.id
        if (currentDest != navFragmentRes)
            navController.navigate(navFragmentRes, bundle, navOptions)
    }
}

private fun canNavigate(controller: NavController, view: View?): Boolean {
    val destinationIdInNavController = controller.currentDestination?.id
    // add tag_navigation_destination_id to your res\values\ids.xml so that it's unique:
    val destinationIdOfThisFragment =
        view?.getTag(R.id.tag_navigation_destination_id) ?: destinationIdInNavController

    // check that the navigation graph is still in 'this' fragment, if not then the app already navigated:
    return if (destinationIdInNavController == destinationIdOfThisFragment) {
        view?.setTag(R.id.tag_navigation_destination_id, destinationIdOfThisFragment)
        true
    } else {
        false
    }
}

fun LifecycleOwner.popUpCurrentFragment(): NavOptions? {
    return if (this is Fragment) NavOptions.Builder()
        .setPopUpTo(findNavController().currentDestination?.id!!, true).build()
    else null
}