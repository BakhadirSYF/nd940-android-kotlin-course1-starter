package com.udacity.shoestore.viewmodels

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    companion object {
        const val NO_ERROR = 0
        const val SHOE_NAME_ERROR = 1
        const val SHOE_SIZE_ERROR = 2
        const val SHOE_COMPANY_ERROR = 3
    }

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

    // Error flags
    private val _emptyFieldFlag = MutableLiveData<Int>()
    val emptyFieldFlag: LiveData<Int>
        get() = _emptyFieldFlag

    init {
        _shoeList.value = ArrayList()
        resetFields()
    }

    // Methods for buttons presses
    fun onCancel() {
        _eventOnCancel.value = true
    }

    // Check shoe name, company and size fields, show error if they are empty.
    // Check shoe description field; use "-" if it's empty.
    fun onSave() {
        when {
            TextUtils.isEmpty(shoeName.value) -> {
                _emptyFieldFlag.value = SHOE_NAME_ERROR
            }
            TextUtils.isEmpty(shoeCompany.value) -> {
                _emptyFieldFlag.value = SHOE_COMPANY_ERROR
            }
            TextUtils.isEmpty(shoeSize.value) -> {
                _emptyFieldFlag.value = SHOE_SIZE_ERROR
            }
            else -> {
                _emptyFieldFlag.value = NO_ERROR
                _shoeList.value?.add(Shoe(shoeName.value.toString(),
                    shoeSize.value!!.toDouble(),
                    shoeCompany.value.toString(),
                    if (TextUtils.isEmpty(shoeDescription.value.toString())) "-" else shoeDescription.value.toString()))
                _eventOnSave.value = true
            }
        }
    }

    fun onShoeSaved() {
        resetFields()
    }

    fun onSaveShoeCancelled() {
        resetFields()
    }

    private fun resetFields() {
        shoeName.value = ""
        shoeCompany.value = ""
        shoeSize.value = ""
        shoeDescription.value = ""
        _emptyFieldFlag.value = NO_ERROR
        _eventOnCancel.value = false
        _eventOnSave.value = false
    }
}