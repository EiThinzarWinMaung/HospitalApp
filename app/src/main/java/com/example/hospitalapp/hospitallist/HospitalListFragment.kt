package com.example.hospitalapp.hospitallist

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hospitalapp.R
import com.example.hospitalapp.model.list.Hospital
import kotlinx.android.synthetic.main.fragment_hospital_list.*
import kotlinx.android.synthetic.main.item_hospital_list.*
import java.text.SimpleDateFormat
import java.util.*
import java.time.LocalDateTime
import java.util.Calendar.getInstance

class HospitalListFragment : Fragment(), HospitalListAdapter.ClickListener {

    lateinit var hospitalListAdapter: HospitalListAdapter
    lateinit var hospitalListViewmodel: HospitalListViewmodel

//    var searchView: SearchView? = null
//    var queryTextListener: OnQueryTextListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hospital_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSpinner.setOnClickListener {
            var action = HospitalListFragmentDirections.actionHospitalListFragmentToSpinnerFragment()
            findNavController().navigate(action)
        }

//        val currentDate = LocalDateTime.now()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
//        val currentDate: String = simpleDateFormat.format(Date())
        val currentDate: String = Date().toString()
        txtCurrentDate.text = "Current Date => " + currentDate

        hospitalListViewmodel = ViewModelProvider(this).get(HospitalListViewmodel::class.java)

        hospitalListAdapter = HospitalListAdapter()

        RecyclerviewHospitalList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = hospitalListAdapter
        }

        hospitalListAdapter.setOnClickListener(this)
        observeViewmodel()
    }

    private fun observeViewmodel() {
        hospitalListViewmodel.getHospitalList().observe(viewLifecycleOwner, Observer { hospital ->
                hospitalListAdapter.updateHospitalList(hospital.hospitals)
        })

        hospitalListViewmodel.getLoading().observe(viewLifecycleOwner, Observer { isLoading ->
            loadingBar.visibility =
                if (isLoading) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }
        })
    }

    override fun onResume() {
        super.onResume()
        hospitalListViewmodel.loadHospitalList()

        var searchValue = edtSearch.text
        edtSearch.addTextChangedListener() {
            Log.d("SearchItem >>>>>>>>", searchValue.toString())
            hospitalListViewmodel.loadSearchHospital(searchValue.toString())
        }
    }

    override fun onClick(hospital: Hospital) {
        var action = HospitalListFragmentDirections.actionHospitalListFragmentToHospitalDetailsFragment(hospital.hospital_id)
        findNavController().navigate(action)
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//
//        inflater.inflate(R.menu.menu_search, menu)
//        val searchItem = menu.findItem(R.id.actionSearch)
//        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
//
//        if (searchItem != null) {
//            searchView = searchItem.actionView as SearchView
//        }
//
//        if (searchView != null) {
//            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
//            searchView!!.queryHint = "Search Hospital..."
//
//           searchView!!.setOnQueryTextListener(object: OnQueryTextListener {
//               override fun onQueryTextSubmit(query: String?): Boolean {
//                   Log.d("onQueryTextSubmit", query.toString())
//                   hospitalListViewmodel.loadSearchHospital(query.toString())
//                   return false
//               }
//
//               override fun onQueryTextChange(newQuery: String?): Boolean {
//                   Log.d("onQueryTextChange", newQuery.toString())
//                   return false
//               }
//           })
//            searchView!!.setOnQueryTextListener(queryTextListener)
//        }
//    }
}
