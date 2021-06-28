package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    var shoeName = MutableLiveData<String>()
    var shoeCompany = MutableLiveData<String>()
    var shoeSize = MutableLiveData<String>()
    var shoeDescription = MutableLiveData<String>()

    /*private val _shoeName = MutableLiveData<String>()
    val shoeName: LiveData<String>
        get() = _shoeName

    private val _shoeCompany = MutableLiveData<String>()
    val shoeCompany: LiveData<String>
        get() = _shoeCompany

    private val _shoeSize = MutableLiveData<Double>()
    val shoeSize: LiveData<Double>
        get() = _shoeSize

    private val _shoeDescription = MutableLiveData<String>()
    val shoeDescription: LiveData<String>
        get() = _shoeDescription*/

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    // Events
    private val _eventOnCancel = MutableLiveData<Boolean>()
    val eventOnCancel: LiveData<Boolean>
        get() = _eventOnCancel

    private val _eventOnSave = MutableLiveData<Boolean>()
    val eventOnSave: LiveData<Boolean>
        get() = _eventOnSave

    init {
        _shoeList.value = ArrayList()
    }

    fun addNewShoe(shoeName: String, company: String, shoeSize: String, description: String) {
        _shoeList.value?.add(Shoe(shoeName, shoeSize.toDouble(), company, description))
    }

    // Methods for buttons presses
    fun onCancel() {
        _eventOnCancel.value = true
    }

    fun onSave() {
        _shoeList.value?.add(Shoe(shoeName.value.toString(), shoeSize.value!!.toDouble(),
            shoeCompany.value.toString(), shoeDescription.value.toString()))

        _eventOnSave.value = true
    }
}