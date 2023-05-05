package com.example.sambhrant_demo_app.model


import com.google.gson.annotations.SerializedName

data class Listing(
    @SerializedName("pagination")
    val pagination: Pagination,
    @SerializedName("results")
    val results: List<Result>
)