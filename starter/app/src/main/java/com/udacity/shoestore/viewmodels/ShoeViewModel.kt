package com.udacity.shoestore.viewmodels

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = ArrayList()
    }

    fun addNewShoe(shoeName: String, company: String, shoeSize: String, description: String) {
        _shoeList.value?.add(Shoe(shoeName, shoeSize.toDouble(), company, description))
    }
}