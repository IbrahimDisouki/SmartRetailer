package com.smartretailer.smartretailer.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import com.smartretailer.smartretailer.R
import com.smartretailer.smartretailer.databinding.FragmentMainBinding
import com.smartretailer.smartretailer.databinding.FragmentSignInBinding

class MainFragment() : Fragment() {


    private lateinit var viewModel: MainViewModel
    private var _binding : FragmentMainBinding? =null
    private val binding get() = _binding!!

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

}
}

