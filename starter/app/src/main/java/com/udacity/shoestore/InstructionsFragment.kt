package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentInstructionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instructions,
            container, false
        )

        binding.instructionsNextButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment())
        }

        setHasOptionsMenu(true)

        return binding.root

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}