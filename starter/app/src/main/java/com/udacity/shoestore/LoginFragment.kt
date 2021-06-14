package com.udacity.shoestore

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login,
            container, false
        )

        binding.buttonLogin.setOnClickListener { view: View ->
            when {
                TextUtils.isEmpty(binding.username.text.toString()) -> {
                    binding.username.error = "Please enter valid username"
                }
                TextUtils.isEmpty(binding.password.text.toString()) -> {
                    binding.password.error = "Please enter valid password"
                }
                else -> {
                    view.findNavController()
                        .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                }
            }
        }

        binding.buttonSignUp.setOnClickListener { view: View ->
            when {
                TextUtils.isEmpty(binding.username.text.toString()) -> {
                    binding.username.error = "Please enter valid username"
                }
                TextUtils.isEmpty(binding.password.text.toString()) -> {
                    binding.password.error = "Please enter valid password"
                }
                else -> {
                    view.findNavController()
                        .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                }
            }
        }

        return binding.root
    }
}