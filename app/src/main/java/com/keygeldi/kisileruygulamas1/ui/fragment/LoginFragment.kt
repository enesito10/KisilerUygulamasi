package com.keygeldi.kisileruygulamas1.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.keygeldi.kisileruygulamas1.R
import androidx.navigation.fragment.findNavController
import com.keygeldi.kisileruygulamas1.databinding.FragmentAnasayfaBinding
import com.keygeldi.kisileruygulamas1.databinding.FragmentLoginEkranBinding
import com.keygeldi.kisileruygulamas1.ui.viewmodel.AnasayfaViewModel
import com.keygeldi.kisileruygulamas1.ui.viewmodel.LoginViewModel

// LoginFragment.kt
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginEkranBinding
    private lateinit var viewModel:LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login_ekran, container, false)

        val loginButton: Button = view.findViewById(R.id.log_button)
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_anasayfaFragment)
        }

        return view
    }
}
