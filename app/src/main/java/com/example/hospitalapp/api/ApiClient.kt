package com.example.hospitalapp.api

import com.example.hospitalapp.model.details.DetailsList
import com.example.hospitalapp.model.details.Hospital
import com.example.hospitalapp.model.list.HospitalList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val BASE_URL = "http://hospitalguideapi.dsv.hoz.mybluehost.me/api/"

    companion object {
        val imgURL = "http://hospitalguideapi.dsv.hoz.mybluehost.me"
    }

    private val apiInterface: ApiInterface

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun getHospital(
    ): Call<HospitalList> {
        return apiInterface.getHospital()
    }

    fun getHospitalDetails(
        hospitalId: Int
    ): Call<DetailsList> {
        return apiInterface.getHospitalDetails(hospitalId)
    }

    fun getSearchHospital(
        hospitalName: String
    ): Call<HospitalList> {
        return apiInterface.getSearchHospital(hospitalName)
    }
}