package com.smartretailer.smartretailer.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.smartretailer.smartretailer.R
import com.smartretailer.smartretailer.databinding.FragmentSignInBinding
import com.smartretailer.smartretailer.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {


    private lateinit var viewModel: SignUpViewModel
    private var _binding :FragmentSignUpBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding=FragmentSignUpBinding.inflate(inflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding.signupbutton.setOnClickListener{
            if (binding.signuppasswordinput.editText!!.text.toString().isEmpty())
            {
                binding.signupconfirmpasswordinput.error="Please enter E-mail"
            }
            else if (binding.signupemailinput.editText!!.text.toString().isEmpty()){
                binding.signupconfirmpasswordinput.error="Please enter Password"
            }
            else if(binding.signupconfirmpasswordinput.editText!!.text.toString().isEmpty()){
                binding.signupconfirmpasswordinput.error="Please enter Confirm Password"
            }
            else if(binding.signuppasswordinput.editText!!.text.toString().equals((binding.signupconfirmpasswordinput.editText!!.text.toString()))){
                 viewModel.signup(binding.signupemailinput.editText!!.text.toString(),binding.signuppasswordinput.editText!!.text.toString())
                }
            else {
                binding.signupconfirmpasswordinput.error="Password and confirm Password don't match"
            }
        }
        viewModel.triggertransition.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
        }
    }

}