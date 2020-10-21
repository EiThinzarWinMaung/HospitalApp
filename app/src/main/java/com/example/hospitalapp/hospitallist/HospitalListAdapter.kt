package com.example.hospitalapp.hospitallist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitalapp.R
import com.example.hospitalapp.api.ApiClient.Companion.imgURL
import com.example.hospitalapp.model.list.Hospital
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_hospital_list.view.*
import kotlin.math.absoluteValue

class HospitalListAdapter(var hospitalList: List<Hospital> = ArrayList<Hospital>()): RecyclerView.Adapter<HospitalListAdapter.HospitalListViewHolder>() {

    var hclickListener: ClickListener? = null

    fun setOnClickListener(clickListener: HospitalListFragment) {
        this.hclickListener = clickListener
    }

    inner class HospitalListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var hospital: Hospital

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(hospital: Hospital) {
            this.hospital = hospital

//            if(hospital.hospital_id == 1) {
//                itemView.txtHospitalList.visibility = View.GONE
//                itemView.imgHospitalList.visibility = View.GONE
//                itemView.CardHospitalList.visibility = View.GONE
//                itemView.CardHospitalList.isVisible = false
//            }

//            else if(hospital.hospital_id == 2) {
//                itemView.txtHospitalList.visibility = View.GONE
//                itemView.imgHospitalList.visibility = View.GONE
//                itemView.CardHospitalList.visibility = View.GONE
//            }

//            else {
                itemView.txtHospitalList.text = hospital.hospital_name
                Picasso.get().load(imgURL + hospital.hospital_image).placeholder(R.drawable.placeholder_image).into(itemView.imgHospitalList)
//            }
        }

        override fun onClick(p0: View?) {
            hclickListener?.onClick(hospital)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_hospital_list, parent, false)
        return HospitalListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalList.size
    }

    override fun onBindViewHolder(holder: HospitalListViewHolder, position: Int) {
        return holder.bind(hospitalList[position])
    }

    fun updateHospitalList(hospitalList: List<Hospital>) {
        this.hospitalList = hospitalList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(hospital: Hospital)
    }
}