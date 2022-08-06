package com.example.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.data.CountryModel
import com.example.countries.retrofit.RetroInstance
import com.example.countries.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    lateinit var liveDataList: MutableLiveData<CountryModel>

    init {
        liveDataList = MutableLiveData()
    }


    fun getLiveDataObserver(): MutableLiveData<CountryModel> {
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance?.create(RetroServiceInterface::class.java)
        retroService?.getCountryList()?.enqueue(object : Callback<CountryModel> {
            override fun onFailure(call: Call<CountryModel>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(
                call: Call<CountryModel>,
                response: Response<CountryModel>
            ) {
                liveDataList.postValue(response.body())
            }
        })


    }
}