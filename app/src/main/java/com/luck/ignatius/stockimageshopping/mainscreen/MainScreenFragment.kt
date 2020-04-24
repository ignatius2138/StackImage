package com.luck.ignatius.stockimageshopping.mainscreen

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.luck.ignatius.stockimageshopping.R
import com.luck.ignatius.stockimageshopping.databinding.FragmentMainScreenBinding
import com.luck.ignatius.stockimageshopping.mainscreen.MainScreenFragmentDirections.*

class MainScreenFragment: Fragment() {

    private val viewModel: MainScreenViewModel by lazy {
        ViewModelProvider(this).get(MainScreenViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMainScreenBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main_screen, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.imagesGrid.adapter = ImageGridAdapter(ImageGridAdapter.OnClickListener{
            viewModel.displayImageDetails(it)
        })
        viewModel.navigateToSelectedImage.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(actionMainScreenFragmentToDetailsScreenFragment(it))
                viewModel.displayImageDetailsComplete()
            }
        })
        setHasOptionsMenu(true)
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