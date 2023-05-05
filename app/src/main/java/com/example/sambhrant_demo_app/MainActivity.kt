package com.example.sambhrant_demo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sambhrant_demo_app.adapter.ListAdapter
import com.example.sambhrant_demo_app.api.ApiService
import com.example.sambhrant_demo_app.api.RetrofitHelper
import com.example.sambhrant_demo_app.model.Result
import com.example.sambhrant_demo_app.repository.ListRepository
import com.example.sambhrant_demo_app.viewmodels.MainViewModel
import com.example.sambhrant_demo_app.viewmodels.MainViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    lateinit var gett: ListRepository
    lateinit var adapter: ListAdapter
    lateinit var list: List<Result>
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recycler = findViewById<RecyclerView>(R.id.recyclerview)


        val apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val repository = ListRepository(apiService)

        var mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.lists.observe(this, Observer {
            list = it
            println(list)

        })
        adapter = ListAdapter(this@MainActivity, list)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this@MainActivity)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.filterPriceAscending -> {
                   gett.getList("priceIncrease")
                    // Handle filter button click for price ascending
                    true
                }
                R.id.filterPriceDescending -> {
                    gett.getList("priceDecrease")
                    // Handle filter button click for price descending
                    true
                }
                R.id.filterDateAscending -> {
                    // Handle filter button click for date ascending
                    gett.getList("dateIncrease")

                    true
                }
                R.id.filterDateDescending -> {
                    gett.getList( "dateDecrease")
                    // Handle filter button click for date descending
                    true
                }
                else -> false
            }
        }

    }
}