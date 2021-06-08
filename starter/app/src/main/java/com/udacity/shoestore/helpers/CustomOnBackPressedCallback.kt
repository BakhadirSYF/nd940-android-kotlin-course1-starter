package com.udacity.shoestore.helpers

import androidx.activity.OnBackPressedCallback
import timber.log.Timber

class CustomOnBackPressedCallback : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
        // disable back button
        Timber.d("handleOnBackPressed")
    }
}