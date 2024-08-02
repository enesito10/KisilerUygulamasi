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
import com.keygeldi.kisileruygulamas1.databinding.FragmentRegisterEkranBinding
import com.keygeldi.kisileruygulamas1.ui.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterEkranBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_ekran, container, false)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.registerViewModel = viewModel
        binding.lifecycleOwner = this

        binding.signFinishButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

            return binding.root

    }
}
