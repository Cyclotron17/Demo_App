package com.example.sambhrant_demo_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sambhrant_demo_app.model.Listing
import com.example.sambhrant_demo_app.model.Result
import com.example.sambhrant_demo_app.repository.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ListRepository ): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getList()
        }
    }

    val lists: LiveData<List<Result>>
        get() = repository.lists

    fun getList(filter:String){
    }


}