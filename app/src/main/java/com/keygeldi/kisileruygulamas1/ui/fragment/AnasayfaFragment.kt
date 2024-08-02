package com.keygeldi.kisileruygulamas1.ui.fragment

import android.content.Context
import android.os.Binder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.internal.ViewUtils.showKeyboard
import com.keygeldi.kisileruygulamas1.R
import com.keygeldi.kisileruygulamas1.data.entity.Kisiler
import com.keygeldi.kisileruygulamas1.databinding.FragmentAnasayfaBinding
import com.keygeldi.kisileruygulamas1.ui.adapter.KisilerAdapter
import com.keygeldi.kisileruygulamas1.ui.viewmodel.AnasayfaViewModel
import com.keygeldi.kisileruygulamas1.ui.viewmodel.KisiKayitViewModel
import com.keygeldi.kisileruygulamas1.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {

    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel:AnasayfaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa,container,false)
        binding.anasayfaFragment = this
        binding.anasayfaToolbarBaslik="Kişiler"

        viewModel.kisilerListesi.observe(viewLifecycleOwner){
            val kisilerAdapter = KisilerAdapter(requireContext(),it,viewModel)
            binding.kisilerAdapter = kisilerAdapter
        }

       /* binding.rv.layoutManager = LinearLayoutManager(requireContext())



        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.kisiKayitGecis)

        } */

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.ara(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.ara(query)
                return true
            }
        })
        return binding.root
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun fabTikla(it:View){
        Navigation.gecisYap(it,R.id.kisiKayitGecis)
    }

    override fun onResume() {
        super.onResume()
        viewModel.kisileriYukle()
    }

}
