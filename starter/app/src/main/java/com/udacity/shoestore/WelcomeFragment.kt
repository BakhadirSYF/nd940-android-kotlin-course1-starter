package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.helpers.CustomOnBackPressedCallback

class WelcomeFragment : Fragment() {

    private lateinit var onBackPressedCallback: CustomOnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome,
            container, false
        )

        binding.buttonNext.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment())
        }

        setHasOptionsMenu(true)

        onBackPressedCallback = CustomOnBackPressedCallback()

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        onBackPressedCallback.remove()
        super.onStop()
    }
}