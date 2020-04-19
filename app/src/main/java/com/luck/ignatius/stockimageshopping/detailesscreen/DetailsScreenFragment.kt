package com.luck.ignatius.stockimageshopping.detailesscreen

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.snackbar.Snackbar
import com.luck.ignatius.stockimageshopping.R
import com.luck.ignatius.stockimageshopping.database.StackImageDatabase
import com.luck.ignatius.stockimageshopping.databinding.FragmentDetailedScreenBinding

class DetailsScreenFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailedScreenBinding.inflate(inflater)
        val data = DetailsScreenFragmentArgs.fromBundle(arguments!!).selectedImage
        val dataSource = StackImageDatabase.getInstance(application).cartTableDao
        val viewModelFactory = DetailsScreenViewModelFactory(data, application, dataSource)
        val detailsViewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailsScreenViewModel::class.java)
        binding.viewModel = detailsViewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

        detailsViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.snackbar_item_added_to_cart),
                    Snackbar.LENGTH_SHORT
                ).show()
                detailsViewModel.doneShowingSnackbar()
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}