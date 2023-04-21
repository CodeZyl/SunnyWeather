package com.sunnyweather.android.ui.place

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.R
import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.databinding.FragmentPlaceBinding


class PlaceFragment : Fragment() {

    lateinit var binding : FragmentPlaceBinding
    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private lateinit var adapter:PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place , container , false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentPlaceBinding.inflate(layoutInflater)
        val layoutManager = LinearLayoutManager(activity)
        activity?.setContentView(binding.root)
        //val recyclerView : RecyclerView = activity.findViewById(R.id.recyclerView)
        binding.recyclerView.layoutManager = layoutManager
        adapter = PlaceAdapter(this , viewModel.placeList)
        binding.recyclerView.adapter = adapter
        val searchPlaceEdit : EditText? = activity?.findViewById(R.id.searchPlaceEdit)
        //Log.d("->>>>>>>>>>>>>>>>>" , "=============================================1")
        searchPlaceEdit?.addTextChangedListener {editable ->
            //Log.d("->>>>>>>>>>>>>>>>>" , "=============================================2")
            val content = editable.toString()
            if(content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            }
            else {
                binding.recyclerView.visibility = View.GONE
                binding.bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }

        }
        viewModel.placeLiveData.observe(this.viewLifecycleOwner , Observer {result ->
            //Log.d("------>>>>>>_" , "$result")
            val places = result.getOrNull()
            if(places != null) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.bgImageView.visibility = View.GONE
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }
            else {
                Toast.makeText(activity,  "未能查询到任何地点" , Toast.LENGTH_LONG).show()
                result.exceptionOrNull()?.printStackTrace()
            }


        })
    }

}