package com.example.countries.retrofit

import com.example.countries.data.CountryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("v1/geo/countries?limit=10")
    fun getCountryList(): Call<CountryModel>

}