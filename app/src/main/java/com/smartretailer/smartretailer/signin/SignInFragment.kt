package com.smartretailer.smartretailer.signin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smartretailer.smartretailer.R
import com.smartretailer.smartretailer.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private val sharedPrefFile = "SmartRetailer"
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
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        activity!!.onBackPressedDispatcher.addCallback(viewLifecycleOwner,object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity!!.finish()
            }})
        val sharedPreferences: SharedPreferences = this.activity!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        if (sharedPreferences.contains(getString(R.string.refreshtoken)))
            {
            val refreshtoken=sharedPreferences.getString(getString(R.string.refreshtoken),"gg")
            viewModel.refreshtoken(refreshtoken)
                findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
            }
        binding.signingotosignupbutton.setOnClickListener { findNavController().navigate(R.id.action_signInFragment_to_signUpFragment) }
        binding.signinbutton.setOnClickListener {
            if(binding.signinemailinput.editText!!.text.isEmpty())
            {
                binding.signinemailinput.error="please enter email"
                binding.signinemailinput.editText!!.addTextChangedListener(object :TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        binding.signinemailinput.error=null
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
            }
            else if(binding.signinpasswordinput.editText!!.text.isEmpty())
            {
                binding.signinpasswordinput.error="please enter password"
                binding.signinpasswordinput.editText!!.addTextChangedListener(object :TextWatcher{
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        binding.signinpasswordinput.error=null
                    }

                    override fun afterTextChanged(p0: Editable?) {

                    }

                })
            }
            else
            {
                viewModel.signin(binding.signinemailinput.editText!!.text.toString(),binding.signinpasswordinput.editText!!.text.toString())
                viewModel.triggertransition.observe(viewLifecycleOwner){
                    viewModel.triggertransition.removeObservers(viewLifecycleOwner)
                    findNavController().navigate(R.id.action_signInFragment_to_mainFragment)

                }
            }

        }

        viewModel.wrongsignininfo.observe(viewLifecycleOwner){
            binding.signinpasswordinput.error="wrong email or password"
            binding.signinpasswordinput.editText!!.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding.signinpasswordinput.error=null
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })
            binding.signinemailinput.editText!!.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding.signinpasswordinput.error=null
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })

        }
    }
}