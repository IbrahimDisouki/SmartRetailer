package com.smartretailer.smartretailer.signin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
            if (binding.signupemailinput.editText!!.text.toString().isEmpty())
            {
                binding.signupemailinput.error="Please enter E-mail"
                binding.signupemailinput.editText!!.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        binding.signupemailinput.error=null
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
            }
            else if (binding.signupemailinput.editText!!.text.toString().isEmpty()){
                binding.signuppasswordinput.error="Please enter Password"
                binding.signuppasswordinput.editText!!.addTextChangedListener(object :TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        binding.signuppasswordinput.error=null
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
            }
            else if(binding.signuppasswordinput.editText!!.text.length <6){
                binding.signuppasswordinput.error="Password too short"
                binding.signuppasswordinput.editText!!.addTextChangedListener(object :TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        if(binding.signuppasswordinput.editText!!.text.length >=6)
                        binding.signuppasswordinput.error=null
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
            }

            else if(binding.signupconfirmpasswordinput.editText!!.text.toString().isEmpty()){
                binding.signupconfirmpasswordinput.error="Please enter Confirm Password"
                binding.signupconfirmpasswordinput.editText!!.addTextChangedListener(object :TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        binding.signupconfirmpasswordinput.error=null
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
            }
            else if(binding.signuppasswordinput.editText!!.text.toString().equals((binding.signupconfirmpasswordinput.editText!!.text.toString()))){
                 viewModel.signup(binding.signupemailinput.editText!!.text.toString(),binding.signuppasswordinput.editText!!.text.toString())
                }
            else {
                binding.signupconfirmpasswordinput.error="Password and confirm Password don't match"
                binding.signupconfirmpasswordinput.editText!!.addTextChangedListener(object :TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        if(binding.signuppasswordinput.editText!!.text.toString().equals((binding.signupconfirmpasswordinput.editText!!.text.toString())))
                        { viewModel.signup(binding.signupemailinput.editText!!.text.toString(),binding.signuppasswordinput.editText!!.text.toString())
                        binding.signupconfirmpasswordinput.error=null
                    }
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
            }
        }
        viewModel.triggertransition.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
        }
        viewModel.triggererror.observe(viewLifecycleOwner){
            binding.signupemailinput.error="email is already in use"
            binding.signupemailinput.editText!!.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding.signupemailinput.error=null
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
        }
    }

}