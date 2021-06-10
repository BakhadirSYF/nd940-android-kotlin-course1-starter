package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeDetailBinding
import com.udacity.shoestore.helpers.CustomOnBackPressedCallback
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    lateinit var onBackPressedCallback: CustomOnBackPressedCallback

    private val viewModel: ShoeViewModel by activityViewModels()

    private var _shoeDetailBinding: ItemShoeDetailBinding? = null
    private val shoeDetailBinding get() = _shoeDetailBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list,
            container, false
        )

        binding.addShoeFab.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        binding.lifecycleOwner = this

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { list ->
            Timber.d("shoe list size = %s", list.size)
            if (list.isNotEmpty()) {
                timberLog(list)

                for (shoe in list) {

//                    var shoeDetailView = inflater.inflate(R.layout.item_shoe_detail, null)
                    _shoeDetailBinding = ItemShoeDetailBinding.inflate(inflater)

                    shoeDetailBinding.shoeItemCompany.text = shoe.company
                    shoeDetailBinding.shoeItemName.text = shoe.name
                    shoeDetailBinding.shoeItemSize.text = resources.getString(R.string.shoe_size_format).format(shoe.size)
//                    shoeDetailBinding.shoeItemSize.text = R.string.shoe_size_format.toString().format(shoe.size)
                    shoeDetailBinding.shoeItemDescription.text = shoe.description

/*
                    var company: TextView = shoeDetailView.findViewById(R.id.shoeItemCompany)
                    var name: TextView = shoeDetailView.findViewById(R.id.shoeItemName)
                    var size: TextView = shoeDetailView.findViewById(R.id.shoeItemSize)
                    var description: TextView =
                        shoeDetailView.findViewById(R.id.shoeItemDescription)

                    company.text = shoe.company
                    name.text = shoe.name
                    size.text = shoe.size.toString()
                    description.text = shoe.description
*/

                    binding.shoeList.addView(shoeDetailBinding.root)

//                    addNewShoe(shoe)
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

    private fun addNewShoe(shoe: Shoe) {
        TODO("Not yet implemented")

//        layoutInflater.inflate(R.layout.item_shoe_detail)
    }

    private fun timberLog(list: MutableList<Shoe>?) {
        Timber.d("first shoe in the list name = %s", list?.get(0)?.name)
        for (shoe in list!!) {
            Timber.d("shoe number %s size is %s", list.indexOf(shoe) + 1, shoe.size)
        }
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