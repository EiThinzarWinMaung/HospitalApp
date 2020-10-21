package com.example.hospitalapp.hospitaldetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hospitalapp.R
import kotlinx.android.synthetic.main.fragment_hospital_details.*

class HospitalDetailsFragment : Fragment() {

    lateinit var hospitalDetailsAdapter: HospitalDetailsAdapter
    lateinit var hospitalDetailsViewmodel: HospitalDetailsViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hospital_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var hospitalDetailsArgs = arguments?.let {
            HospitalDetailsFragmentArgs.fromBundle(it)
        }

        var hospitalDetailsId = hospitalDetailsArgs?.hospitalID

        hospitalDetailsViewmodel = ViewModelProvider(this).get(HospitalDetailsViewmodel::class.java)
        hospitalDetailsViewmodel.loadHospitalDetails(hospitalDetailsId!!)

        hospitalDetailsAdapter = HospitalDetailsAdapter()

        RecyclerviewHospitalDetails.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = hospitalDetailsAdapter
        }
        observeViewmodel()
    }

    private fun observeViewmodel() {
        hospitalDetailsViewmodel.getHospitalDetails().observe(viewLifecycleOwner, Observer { hospitalDetails ->
            hospitalDetailsAdapter.updateHospitalDetails(hospitalDetails.hospital.specialities)
            txtHospitalDetailsName.text = hospitalDetails.hospital.hospital_name
        })
    }
}