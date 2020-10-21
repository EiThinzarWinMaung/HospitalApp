package com.example.hospitalapp.api

import com.example.hospitalapp.model.details.DetailsList
import com.example.hospitalapp.model.details.Hospital
import com.example.hospitalapp.model.list.HospitalList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("hospital")
    fun getHospital(
    ): Call<HospitalList>

    @GET("hospital/{hospital_id}")
    fun getHospitalDetails(
        @Path("hospital_id") hospitalId: Int
    ): Call<DetailsList>

    @GET("hospital")
    fun getSearchHospital(
        @Query("keyword") hospitalName: String
    ): Call<HospitalList>
}