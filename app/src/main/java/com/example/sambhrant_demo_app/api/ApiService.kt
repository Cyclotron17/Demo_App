package com.example.sambhrant_demo_app.api

import com.example.sambhrant_demo_app.model.Listing
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("default/dynamodb-writer")
    suspend fun getList(): Response<Listing>
}