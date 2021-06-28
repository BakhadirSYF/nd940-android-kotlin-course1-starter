package com.udacity.shoestore

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.helpers.CustomOnBackPressedCallback
import com.udacity.shoestore.viewmodels.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var onBackPressedCallback: CustomOnBackPressedCallback

    private lateinit var binding: FragmentShoeDetailBinding

    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail,
            container, false)

        // Set the viewModel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.shoeViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        viewModel.eventOnCancel.observe(viewLifecycleOwner, Observer { isCancel ->
            if (isCancel) {
                viewModel.onSaveShoeCancelled()
                NavHostFragment.findNavController(this)
                    .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
            }
        })

        viewModel.eventOnSave.observe(viewLifecycleOwner, Observer { isSave ->
            if (isSave) {
                onSaveButtonClick()
                viewModel.onShoeSaved()
            }
        })

        viewModel.emptyFieldFlag.observe(viewLifecycleOwner, Observer { emptyFieldFlag ->
            when (emptyFieldFlag) {
                ShoeViewModel.SHOE_NAME_ERROR -> {
                    binding.shoeName.error = "Please enter valid shoe name"
                }
                ShoeViewModel.SHOE_COMPANY_ERROR -> {
                    binding.company.error = "Please enter valid company name"
                }
                ShoeViewModel.SHOE_SIZE_ERROR -> {
                    binding.shoeSize.error = "Please enter valid shoe size"
                }
            }
        })

        setHasOptionsMenu(true)

        onBackPressedCallback = CustomOnBackPressedCallback()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        return binding.root

    }

    private fun onSaveButtonClick() {
        NavHostFragment.findNavController(this)
            .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
    }

    override fun onStop() {
        onBackPressedCallback.remove()
        super.onStop()
    }

}