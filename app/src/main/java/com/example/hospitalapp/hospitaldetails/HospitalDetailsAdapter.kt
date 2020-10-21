package com.example.hospitalapp.hospitaldetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalapp.R
import com.example.hospitalapp.api.ApiClient.Companion.imgURL
import com.example.hospitalapp.model.details.Hospital
import com.example.hospitalapp.model.details.Speciality
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hospital_details.view.*

class HospitalDetailsAdapter(var hospitalDetailsList: List<Speciality> = ArrayList<Speciality>()): RecyclerView.Adapter<HospitalDetailsAdapter.HospitalDetailsViewHolder>() {

    class HospitalDetailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(speciality: Speciality) {
            itemView.txtHospitalDetailsSpeciality.text = speciality.speciality_mname
            Picasso.get().load(imgURL + speciality.speciality_image).placeholder(R.drawable.placeholder_image).into(itemView.imgHospitalDetailsSpeciality)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalDetailsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_hospital_details, parent, false)
        return HospitalDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalDetailsList.size
    }

    override fun onBindViewHolder(holder: HospitalDetailsViewHolder, position: Int) {
        return holder.bind(hospitalDetailsList[position])
    }

    fun updateHospitalDetails(hospitalDetailsList: List<Speciality>) {
        this.hospitalDetailsList = hospitalDetailsList
        notifyDataSetChanged()
    }
}