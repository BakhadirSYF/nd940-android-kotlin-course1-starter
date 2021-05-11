package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,
                container, false)

        binding.buttonLogin.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }

        binding.buttonSignUp.setOnClickListener { view: View ->
            view.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }

//        setHasOptionsMenu(true)

        return binding.root

    }


}