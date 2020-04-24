package com.luck.ignatius.stockimageshopping

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luck.ignatius.stockimageshopping.databinding.FragmentLoginBinding

class LoginScreenFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
        binding.enterButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (email != "" || password != "") {
                if (email == sharedPreferences!!.getString("$email+loginEmail", "loh")) {
                    if (password == sharedPreferences.getString("$email+loginPassword", "loh")) {

                        findNavController().navigate(LoginScreenFragmentDirections.actionLogInFragmentToCartFragment(email))
                        val editor = sharedPreferences.edit()
                        editor.putString("accountName", email)
                        editor.putBoolean("isLoggedIn", true)
                        editor.apply()
                        Toast.makeText(context, "$email logged in :)))", Toast.LENGTH_LONG).show()
                    } else Toast.makeText(context, "Error! Wrong email or password", Toast.LENGTH_LONG).show()
                } else Toast.makeText(context, "Error! Wrong email or password", Toast.LENGTH_LONG).show()
            } else Toast.makeText(context, "Enter all fields", Toast.LENGTH_LONG).show()
        }

        binding.toRegisterScreenButton.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_registerScreenFragment)
        }

        return binding.root
    }
}