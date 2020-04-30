package com.luck.ignatius.stockimageshopping

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luck.ignatius.stockimageshopping.database.StackImageDatabase
import com.luck.ignatius.stockimageshopping.databinding.FragmentLoginBinding
import kotlinx.coroutines.*

class LoginScreenFragment: Fragment() {
    lateinit var binding: FragmentLoginBinding
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private lateinit var application: Application
    private lateinit var database: StackImageDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        application = requireNotNull(this.activity).application
        database = StackImageDatabase.getInstance(application)
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val sharedPreferences = activity!!.getPreferences(Context.MODE_PRIVATE)

        binding.enterButton.setOnClickListener {
            logInMethod(sharedPreferences)
        }

        binding.toRegisterScreenButton.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registerScreenFragment)
        }

        return binding.root
    }

    private fun logInMethod(sharedPreferences: SharedPreferences) {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        if (email != "" || password != "") {
            if (email == sharedPreferences.getString("$email+loginEmail", "loh")) {
                if (password == sharedPreferences.getString("$email+loginPassword", "loh")) {
                    findNavController().navigate(LoginScreenFragmentDirections.actionLogInFragmentToCartFragment(email))
                    val editor = sharedPreferences.edit()
                    editor.putString("accountName", email)
                    editor.putBoolean("isLoggedIn", true)
                    editor.apply()
                    uiScope.launch {
                        withContext(Dispatchers.IO) {
                            database.cartTableDao.updateNotLoggedInAccount(sharedPreferences.getString("accountName", "ERROR!!!")!!, "cart", "not logged in")
                        }
                    }
                    Toast.makeText(context, "$email logged in :)))", Toast.LENGTH_LONG).show()
                } else Toast.makeText(context, "Error! Wrong email or password", Toast.LENGTH_LONG).show()
            } else Toast.makeText(context, "Error! Wrong email or password", Toast.LENGTH_LONG).show()
        } else Toast.makeText(context, "Enter all fields", Toast.LENGTH_LONG).show()
    }

}