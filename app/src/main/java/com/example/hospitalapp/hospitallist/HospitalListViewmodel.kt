package com.example.hospitalapp.hospitallist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hospitalapp.api.ApiClient
import com.example.hospitalapp.model.list.HospitalList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalListViewmodel:ViewModel() {

    private var hospitalList: MutableLiveData<HospitalList> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getHospitalList(): LiveData<HospitalList> = hospitalList
    fun getLoading(): LiveData<Boolean> = loading

    fun loadHospitalList() {
        var apiClient = ApiClient()
        var apiCall = apiClient.getHospital()
        apiCall.enqueue(object: Callback<HospitalList> {
            override fun onFailure(call: Call<HospitalList>, t: Throwable) {
                loading.value = false
                Log.d("Error >>>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<HospitalList>, response: Response<HospitalList>) {
                loading.value = false
                hospitalList.value = response.body()
            }
        })
    }

    fun loadSearchHospital(hospitalName: String) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getSearchHospital(hospitalName)
        apiCall.enqueue(object: Callback<HospitalList> {
            override fun onFailure(call: Call<HospitalList>, t: Throwable) {
                Log.d("Error >>>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<HospitalList>, response: Response<HospitalList>) {
                hospitalList.value = response.body()
                Log.d("Success >>>>>>>>>", response.body().toString())
            }
        })
    }
}