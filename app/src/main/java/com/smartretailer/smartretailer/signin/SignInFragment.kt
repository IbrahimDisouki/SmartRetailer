package com.smartretailer.smartretailer.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.smartretailer.smartretailer.R
import com.smartretailer.smartretailer.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {


    private lateinit var viewModel: SignInViewModel
    private var _binding :FragmentSignInBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

         _binding=FragmentSignInBinding.inflate(inflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
       // activity!!.actionBar!!.hide()
        binding.signingotosignupbutton.setOnClickListener { findNavController().navigate(R.id.action_signInFragment_to_signUpFragment) }
        binding.signinbutton.setOnClickListener {
            if(binding.signinemailinput.editText!!.text.isEmpty())
            {
                binding.signinpasswordinput.error="please enter email"
            }
            else if(binding.signinpasswordinput.editText!!.text.isEmpty())
            {
                binding.signinpasswordinput.error="please enter password"
            }
            else
            {
                viewModel.signin(binding.signinemailinput.editText!!.text.toString(),binding.signinpasswordinput.editText!!.text.toString())
                viewModel.triggertransition.observe(viewLifecycleOwner){
                    findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
                }
            }

        }
    }
}