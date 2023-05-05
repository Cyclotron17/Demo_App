package com.example.sambhrant_demo_app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sambhrant_demo_app.api.ApiService
import com.example.sambhrant_demo_app.model.Listing
import com.example.sambhrant_demo_app.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListRepository(private val listservice: ApiService) {
    private val listLiveData = MutableLiveData<List<Result>>()
    lateinit var list :List<Result>
    val lists: LiveData<List<Result>>
        get() = listLiveData

    suspend fun getList(page: Int) {
        val result = listservice.getList()
        if (result.body() != null) {
            list=result.body()!!.results
            listLiveData.postValue(list)
        }
    }

    fun getList(filter: String): LiveData<List<Result>> {
        when (filter) {
            "priceIncrease" -> {
                // Sort by price in ascending order
                val sortedList = list.sortedBy { it.price }
                listLiveData.postValue(sortedList)
            }
            "priceDecrease" -> {
                // Sort by price in descending order
                val sortedList = list.sortedByDescending { it.price }
                listLiveData.postValue(sortedList)
            }
            "dateIncrease" -> {
                listLiveData.postValue(list.sortedBy { it.createdAt })
            }
            "dateDecrease" -> {
                listLiveData.postValue(list.sortedByDescending { it.createdAt })
            }
            else -> {
                // No filter specified, return the original list
                listLiveData.postValue(list)
            }
        }
        return listLiveData
    }
}
