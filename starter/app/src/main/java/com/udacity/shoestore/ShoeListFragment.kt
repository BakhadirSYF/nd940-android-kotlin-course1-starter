package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeDetailBinding
import com.udacity.shoestore.helpers.CustomOnBackPressedCallback
import com.udacity.shoestore.viewmodels.ShoeViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var onBackPressedCallback: CustomOnBackPressedCallback

    private val viewModel: ShoeViewModel by activityViewModels()

    private var _shoeDetailBinding: ItemShoeDetailBinding? = null
    private val shoeDetailBinding get() = _shoeDetailBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list,
            container, false
        )

        binding.addShoeFab.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        binding.lifecycleOwner = this

        viewModel.shoeList.observe(viewLifecycleOwner, { list ->
            Timber.d("shoe list size = %s", list.size)
            if (list.isNotEmpty()) {
                for (shoe in list) {
                    _shoeDetailBinding =
                        DataBindingUtil.inflate(inflater, R.layout.item_shoe_detail, null, false)

                    shoeDetailBinding.shoeItemCompany.text = shoe.company
                    shoeDetailBinding.shoeItemName.text = shoe.name
                    shoeDetailBinding.shoeItemSize.text =
                        resources.getString(R.string.shoe_size_format).format(shoe.size)
                    shoeDetailBinding.shoeItemDescription.text = shoe.description

                    binding.shoeList.addView(shoeDetailBinding.root)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_action_logout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        onBackPressedCallback.remove()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _shoeDetailBinding = null
    }
}