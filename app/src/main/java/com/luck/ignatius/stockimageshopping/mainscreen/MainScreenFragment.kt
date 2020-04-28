package com.luck.ignatius.stockimageshopping.mainscreen

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.navigation.NavigationView
import com.luck.ignatius.stockimageshopping.R
import com.luck.ignatius.stockimageshopping.databinding.FragmentMainScreenBinding
import com.luck.ignatius.stockimageshopping.mainscreen.MainScreenFragmentDirections.actionMainScreenFragmentToDetailsScreenFragment

class MainScreenFragment: Fragment()  {

    private val viewModel: MainScreenViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this,MainScreenViewModelFactory(activity.application)).get(MainScreenViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMainScreenBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_main_screen, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val navigationView = binding.navigationView
        filterSearchResults(navigationView)

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

    private fun filterSearchResults(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.allImagesMenu -> {
                    viewModel.imageType = null
                    viewModel.updateRecyclerView()
                    return@setNavigationItemSelectedListener true
                }
                R.id.photosMenu -> {
                    viewModel.imageType = "photo"
                    viewModel.updateRecyclerView()
                    return@setNavigationItemSelectedListener true
                }
                R.id.vectorGraphicMenu -> {
                    viewModel.imageType = "vector"
                    viewModel.updateRecyclerView()
                    return@setNavigationItemSelectedListener true
                }
                R.id.anyOrientationMenu -> {
                    viewModel.orientation = null
                    viewModel.updateRecyclerView()
                    return@setNavigationItemSelectedListener true
                }
                R.id.horizontalMenu -> {
                    viewModel.orientation = "horizontal"
                    viewModel.updateRecyclerView()
                    return@setNavigationItemSelectedListener true
                }
                R.id.verticalMenu -> {
                    viewModel.orientation = "vertical"
                    viewModel.updateRecyclerView()
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem.actionView as SearchView
        searchView.imeOptions = searchView.imeOptions or EditorInfo.IME_FLAG_NO_EXTRACT_UI
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.query = p0
                viewModel.updateRecyclerView()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cartFragment -> {
                val navController = findNavController()
                return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
        }
}