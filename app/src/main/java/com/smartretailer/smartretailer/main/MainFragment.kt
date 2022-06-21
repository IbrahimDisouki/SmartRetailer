package com.smartretailer.smartretailer.main

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.smartretailer.smartretailer.R
import com.smartretailer.smartretailer.databinding.FragmentMainBinding
import com.smartretailer.smartretailer.helpers.Singedinuser


class MainFragment() : Fragment() {


    private lateinit var viewModel: MainViewModel
    private var _binding : FragmentMainBinding? =null
    private val binding get() = _binding!!
    private val sharedPrefFile = "SmartRetailer"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding=FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        activity!!.onBackPressedDispatcher.addCallback(viewLifecycleOwner,object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
               activity!!.finish()
            }

        })
        binding.signoutbutton.setOnClickListener {
            val sharedPreferences: SharedPreferences = this.context!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().commit()
            Singedinuser.delete()
            findNavController().navigate(R.id.action_mainFragment_to_signInFragment)
        }

}

    override fun onStop() {
        super.onStop()

    }
}

