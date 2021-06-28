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
                NavHostFragment.findNavController(this)
                    .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
            }
        })

        viewModel.eventOnSave.observe(viewLifecycleOwner, Observer { isSave ->
            if (isSave) {
                // TODO: implement on Save button click
                when {
                    TextUtils.isEmpty(binding.shoeName.text.toString()) -> {
                        binding.shoeName.error = "Please enter valid shoe name"
                    }
                    TextUtils.isEmpty(binding.shoeSize.text.toString()) -> {
                        binding.shoeSize.error = "Please enter valid shoe size"
                    }
                    else -> {
                        onSaveButtonClick()
                    }
                }
            }
        })

        /*binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }*/

        /*binding.saveButton.setOnClickListener { view: View ->
            when {
                TextUtils.isEmpty(binding.shoeName.text.toString()) -> {
                    binding.shoeName.error = "Please enter valid shoe name"
                }
                TextUtils.isEmpty(binding.shoeSize.text.toString()) -> {
                    binding.shoeSize.error = "Please enter valid shoe size"
                }
                else -> {
                    onSaveButtonClick(
                        view, binding.shoeName.text.toString(),
                        binding.company.text.toString(),
                        binding.shoeSize.text.toString(),
                        binding.description.text.toString()
                    )
                }
            }
        }*/

        setHasOptionsMenu(true)

        onBackPressedCallback = CustomOnBackPressedCallback()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        return binding.root

    }

    private fun onSaveButtonClick() {
        /*viewModel.addNewShoe(
            shoeName,
            if (TextUtils.isEmpty(company)) "-" else company,
            shoeSize,
            if (TextUtils.isEmpty(description)) "-" else description
        )*/
        NavHostFragment.findNavController(this)
            .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
    }

    override fun onStop() {
        onBackPressedCallback.remove()
        super.onStop()
    }

}