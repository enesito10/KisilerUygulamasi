package com.keygeldi.kisileruygulamas1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.keygeldi.kisileruygulamas1.R
import com.keygeldi.kisileruygulamas1.databinding.FragmentLoginEkranBinding
import com.keygeldi.kisileruygulamas1.ui.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginEkranBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_ekran, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        binding.logButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_anasayfaFragment)
        }

        binding.signButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
}
