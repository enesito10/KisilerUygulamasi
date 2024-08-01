package com.keygeldi.kisileruygulamas1.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.keygeldi.kisileruygulamas1.R
import com.keygeldi.kisileruygulamas1.data.entity.Kisiler
import com.keygeldi.kisileruygulamas1.databinding.CardTasarimBinding
import com.keygeldi.kisileruygulamas1.databinding.FragmentAnasayfaBinding
import com.keygeldi.kisileruygulamas1.ui.fragment.AnasayfaFragmentDirections
import com.keygeldi.kisileruygulamas1.ui.viewmodel.AnasayfaViewModel
import com.keygeldi.kisileruygulamas1.util.gecisYap

class KisilerAdapter(var mContext: Context, var kisilerListesi:List<Kisiler>, var viewModel:AnasayfaViewModel)
    :RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu>(){

    inner class CardTasarimTutucu(var tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding:CardTasarimBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.card_tasarim,parent,false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
         val kisi = kisilerListesi.get(position)
         val t = holder.tasarim

        t.kisiNesnesi =kisi

        t.cardViewSatir.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.kisiDetayGecis(kisi)
            Navigation.gecisYap(it,gecis)

        }

        t.imageViewSil.setOnClickListener {
            val builder = AlertDialog.Builder(mContext)
            builder.setMessage("${kisi.kisi_ad} silinsin mi?")
                .setNegativeButton("EVET") { dialog, id ->
                    viewModel.sil(kisi.kisi_id)
                }
                .setPositiveButton("HAYIR") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }

    }

    override fun getItemCount(): Int { // kaç veri gösterilicek
        return kisilerListesi.size
    }


}