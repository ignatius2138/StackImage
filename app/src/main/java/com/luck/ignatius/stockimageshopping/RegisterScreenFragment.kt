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
import com.luck.ignatius.stockimageshopping.databinding.FragmentRegisterScreenBinding

class RegisterScreenFragment: Fragment(){
    private lateinit var binding: FragmentRegisterScreenBinding
    //var masterKeyAlias: String = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_screen, container, false)
        /*val sharedPreferences = EncryptedSharedPreferences.create("secret_shared_prefs", masterKeyAlias, context!!,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)*/
        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)

        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val repeatPassword = binding.repeatPasswordeEditText.text.toString()
            if (email != "" && password != "" && repeatPassword != "") {
                if (password == repeatPassword) {
                    val editor = sharedPreferences!!.edit()
                    editor.putString("$email+loginEmail", email)
                    editor.putString("$email+loginPassword", password)
                    editor.apply()
                    Toast.makeText(activity, "Thanks for registration. You can now Log In", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_registerScreenFragment_to_logInFragment)
                } else Toast.makeText(activity, "Error! Passwords don't match", Toast.LENGTH_LONG).show()
            } else Toast.makeText(activity, "Error! Empty fields", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }
}