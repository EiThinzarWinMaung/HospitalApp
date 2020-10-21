package com.example.hospitalapp.spinner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hospitalapp.R
import com.example.hospitalapp.hospitallist.HospitalListAdapter
import com.example.hospitalapp.hospitallist.HospitalListViewmodel
import com.example.hospitalapp.model.list.Hospital
import com.example.hospitalapp.model.list.HospitalList
import kotlinx.android.synthetic.main.fragment_hospital_list.view.*
import kotlinx.android.synthetic.main.fragment_spinner.*
import kotlinx.android.synthetic.main.fragment_spinner.view.*
import java.util.*
import kotlin.collections.ArrayList

class SpinnerFragment : Fragment() {

    private var hospitalArray: List<Hospital> = ArrayList<Hospital>()
//    lateinit var hospitalListAdapter: HospitalListAdapter

    lateinit var hospitalListViewmodel: HospitalListViewmodel

    lateinit var spinnerHospital: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_spinner, container, false)

        spinnerHospital = view.findViewById(R.id.SpinnerHospital)

        view.btnChooseHospital.setOnClickListener {
//            txtSpinnerResult.text = spinnerHospital?.selectedItem?.toString()
            txtSpinnerResult.text =spinnerHospital?.selectedItemId?.toString()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    fun observeViewModel() {
        hospitalListViewmodel = ViewModelProvider(this).get(HospitalListViewmodel::class.java)
        hospitalListViewmodel.loadHospitalList()
        hospitalListViewmodel.getHospitalList().observe(viewLifecycleOwner, Observer { hospital ->
            hospitalArray = hospital.hospitals

//            Log.d("First >>>>>>>", hospitalArray.get(0).hospital_name)
//            Log.d("Second >>>>>>>", hospitalArray.get(1).hospital_name)
//            Log.d("Third >>>>>>>", hospitalArray.get(2).hospital_name)

            var data: MutableList<String> = ArrayList()

            data.add(0, "Choose Hospital")
            data.add(1, hospitalArray.get(0).hospital_name)
            data.add(2, hospitalArray.get(1).hospital_name)
            data.add(3, hospitalArray.get(2).hospital_name)
            data.add(4, hospitalArray.get(3).hospital_name)
            data.add(5, hospitalArray.get(4).hospital_name)
            data.add(6, hospitalArray.get(5).hospital_name)
            data.add(7, hospitalArray.get(6).hospital_name)
            data.add(8, hospitalArray.get(7).hospital_name)
            data.add(9, hospitalArray.get(8).hospital_name)
            data.add(10, hospitalArray.get(9).hospital_name)
            data.add(11, hospitalArray.get(10).hospital_name)
            data.add(12, hospitalArray.get(11).hospital_name)

//            for (item in 0..(hospitalArray.size)-1) {
//                data.add(1, hospitalArray.get(item).hospital_name)
//            }

//            hospitalArray.forEach {
//                data.add(1, it.hospital_id.toString())
//            }

            spinnerHospital.adapter = context?.let {
                ArrayAdapter<String>(it, R.layout.support_simple_spinner_dropdown_item, data)
            }
        })
    }
}