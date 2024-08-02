package com.keygeldi.kisileruygulamas1.ui.fragment

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.keygeldi.kisileruygulamas1.R
import com.keygeldi.kisileruygulamas1.databinding.FragmentRegisterEkranBinding
import com.keygeldi.kisileruygulamas1.room.UserDao
import com.keygeldi.kisileruygulamas1.room.UserDatabase
import com.keygeldi.kisileruygulamas1.ui.viewmodel.RegisterViewModel
import com.keygeldi.kisileruygulamas1.ui.viewmodel.RegisterViewModelFactory


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterEkranBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_ekran, container, false)

        val application = requireNotNull(this.activity).application
        val userDao = UserDatabase.getDatabase(application).userDao()
        val viewModelFactory = RegisterViewModelFactory(userDao)

        viewModel = ViewModelProvider(this,RegisterViewModelFactory(userDao))[RegisterViewModel::class.java]
        binding.registerViewModel = viewModel
        binding.lifecycleOwner = this

        binding.signFinishButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.setUsername(username)
                viewModel.setPassword(password)
                viewModel.register()
            } else {
                Toast.makeText(context, "Kullanıcı adı ve şifre boş olamaz", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModel.registrationResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage -> Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show()
                }
            }
        }
        return binding.root

    }
}
