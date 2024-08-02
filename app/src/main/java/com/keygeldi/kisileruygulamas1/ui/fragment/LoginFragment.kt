package com.keygeldi.kisileruygulamas1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.keygeldi.kisileruygulamas1.R
import com.keygeldi.kisileruygulamas1.databinding.FragmentLoginEkranBinding
import com.keygeldi.kisileruygulamas1.room.UserDatabase
import com.keygeldi.kisileruygulamas1.ui.viewmodel.LoginViewModel
import com.keygeldi.kisileruygulamas1.ui.viewmodel.LoginViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginEkranBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_ekran, container, false)

        val userDao = UserDatabase.getDatabase(requireContext()).userDao()
        viewModel = ViewModelProvider(this, LoginViewModelFactory(userDao))[LoginViewModel::class.java]
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        // Giriş yapma işlemi için gözlemciler
        viewModel.loginResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                findNavController().navigate(R.id.action_loginFragment_to_anasayfaFragment)
            } else {
                Snackbar.make(binding.root, "Kullanıcı adı veya şifre yanlış", Snackbar.LENGTH_LONG).show()
            }
        }

        // Giriş yapma işlemi başlatılır
        binding.logButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            viewModel.login(username, password)
        }

        // Kayıt olma butonuna tıklama
        binding.signButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
}
