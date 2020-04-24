package com.luck.ignatius.stockimageshopping.cart

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.luck.ignatius.stockimageshopping.R
import com.luck.ignatius.stockimageshopping.database.StackImageDatabase
import com.luck.ignatius.stockimageshopping.databinding.FragmentCartBinding

class CartFragment: Fragment() {
    lateinit var cartViewModel: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentCartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = StackImageDatabase.getInstance(application).cartTableDao
        val viewModelFactory = CartViewModelFactory(dataSource, application)
        cartViewModel = ViewModelProvider(this, viewModelFactory).get(CartViewModel::class.java)
        binding.viewModel = cartViewModel
        val adapter = CartListAdapter(CartListAdapter.CartItemListener {
            cartViewModel.onCartItemClicked(it)
        })
        binding.cartItemRecycler.adapter = adapter
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)

        cartViewModel.cartItems.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        cartViewModel.navigateToCart.observe(viewLifecycleOwner, Observer {
            if (it){
                findNavController().navigate(CartFragmentDirections.actionCartFragmentToLogInFragment())
                Toast.makeText(context, "You need to be logged in to buy items", Toast.LENGTH_LONG).show()
                cartViewModel.doneNavigating()
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cart_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logInFragment -> {
                val navController = findNavController()
                return item.onNavDestinationSelected(navController)
            }
            R.id.showHistoryManuItem -> {
                if (::cartViewModel.isInitialized) {
                    cartViewModel.showPurchaseHistory()
                }
                true
            }
            R.id.clearCartMenuItem -> {
                if (::cartViewModel.isInitialized) {
                    cartViewModel.clearCart()
                }
                true
            }
            R.id.showCartMenuItem -> {
                if (::cartViewModel.isInitialized) {
                    cartViewModel.showCart()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}