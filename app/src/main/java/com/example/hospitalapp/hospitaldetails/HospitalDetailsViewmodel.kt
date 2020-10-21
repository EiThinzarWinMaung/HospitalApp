package com.example.hospitalapp.hospitaldetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hospitalapp.api.ApiClient
import com.example.hospitalapp.model.details.DetailsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalDetailsViewmodel: ViewModel() {

    private var hospitalDetails: MutableLiveData<DetailsList> = MutableLiveData()

    fun getHospitalDetails(): LiveData<DetailsList> = hospitalDetails

    fun loadHospitalDetails(id: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getHospitalDetails(id)
        apiCall.enqueue(object: Callback<DetailsList> {
            override fun onFailure(call: Call<DetailsList>, t: Throwable) {
                Log.d("Error >>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<DetailsList>, response: Response<DetailsList>) {
                hospitalDetails.value = response.body()
                Log.d("Success >>>>>>>>", response.body().toString())
            }

        })
    }
}