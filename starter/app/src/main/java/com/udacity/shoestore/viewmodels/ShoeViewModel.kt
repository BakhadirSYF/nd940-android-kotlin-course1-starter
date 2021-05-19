package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    /*companion object {
        private const val DEFAULT_LIST_SIZE = 5
    }*/

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = ArrayList()
//        setupInventory()
    }

    private fun setupInventory() {
        _shoeList.value = getExistingInventory()//existingShoeList
    }

    private fun getExistingInventory(): MutableList<Shoe>? {
        val inventoryList = ArrayList<Shoe>()
        inventoryList.add(Shoe("Redds", 10.5, "Nike", "Classic Redds"))
        inventoryList.add(Shoe("Biggs", 14.0, "Nike", "Largest shoes in inventory"))
        inventoryList.add(Shoe("Runns", 9.0, "Adidas", "Perfect for running"))
        inventoryList.add(Shoe("Smalls", 6.0, "Skechers", "Smallest shoes in inventory"))
        inventoryList.add(Shoe("Classics", 8.5, "Crocs", "Classic Crocs"))
        inventoryList.add(Shoe("Flipps", 11.0, "Flops", "Just flip flops"))
        return inventoryList
    }

    fun addNewShoe(shoeName: String, company: String, shoeSize: String, description: String) {
        _shoeList.value?.add(Shoe(shoeName, shoeSize.toDouble(), company, description))
    }

    // Extension function
    /*private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }*/
}