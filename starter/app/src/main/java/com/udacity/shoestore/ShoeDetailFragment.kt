package com.udacity.shoestore

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.helpers.CustomOnBackPressedCallback
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    lateinit var onBackPressedCallback: CustomOnBackPressedCallback

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail,
            container, false
        )

        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.saveButton.setOnClickListener { view: View ->
            onSaveButtonClick(
                view, binding.shoeName.text.toString(),
                binding.company.text.toString(),
                binding.shoeSize.text.toString(),
                binding.description.text.toString()
            )
        }

        setHasOptionsMenu(true)

        onBackPressedCallback = CustomOnBackPressedCallback()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        return binding.root

    }

    private fun onSaveButtonClick(
        view: View,
        shoeName: String,
        company: String,
        shoeSize: String,
        description: String
    ) {
//        shoeSize = if(TextUtils.isEmpty(shoeSize)) "0" else shoeSize
        viewModel.addNewShoe(shoeName, company, if(TextUtils.isEmpty(shoeSize)) "0" else shoeSize, description)
        view.findNavController()
            .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
    }

    override fun onStop() {
        onBackPressedCallback.remove()
        super.onStop()
    }

}