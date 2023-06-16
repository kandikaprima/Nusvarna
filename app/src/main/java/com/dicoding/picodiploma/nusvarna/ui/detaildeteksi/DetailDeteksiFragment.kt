package com.dicoding.picodiploma.nusvarna.ui.detaildeteksi

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.adapter.ListBajuAdatAdapter
import com.dicoding.picodiploma.nusvarna.adapter.ListEvarnaAdapter
import com.dicoding.picodiploma.nusvarna.adapter.WisataAdapter
import com.dicoding.picodiploma.nusvarna.data.BajuAdat
import com.dicoding.picodiploma.nusvarna.data.DataDetailDeteksi
import com.dicoding.picodiploma.nusvarna.data.DataDetailDeteksiData.dataDetail
import com.dicoding.picodiploma.nusvarna.data.DataWisata
import com.dicoding.picodiploma.nusvarna.data.EventCard
import com.dicoding.picodiploma.nusvarna.databinding.FragmentBerandaBinding
import com.dicoding.picodiploma.nusvarna.databinding.FragmentDetailDeteksiBinding
import com.dicoding.picodiploma.nusvarna.response.DetailResponse
import com.dicoding.picodiploma.nusvarna.ui.main.fragment.beranda.BerandaFragment

class DetailDeteksiFragment : Fragment() {

    private lateinit var binding: FragmentDetailDeteksiBinding
    private var predicted: String = ""

    private lateinit var rvWisata: RecyclerView
    private val listWisata = ArrayList<DataWisata>()

    private lateinit var rvMakanan: RecyclerView
    private val listMakanan = ArrayList<DataWisata>()

    private lateinit var rvBangunan: RecyclerView
    private val listBangunan = ArrayList<DataWisata>()

    private lateinit var rvBaju: RecyclerView
    private val listBaju = ArrayList<BajuAdat>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailDeteksiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val expandableTextView = binding.deskripsiAdat
        val toggleText = binding.lihatSelengkapnya
        rvWisata = binding.rvTempatwisata
        rvWisata.setHasFixedSize(true)
        rvMakanan = binding.rvMakanankhas
        rvMakanan.setHasFixedSize(true)
        rvBangunan = binding.rvBangunanbersejarah
        rvBangunan.setHasFixedSize(true)
        rvBaju = binding.rvBajuadatlainnya
        rvBaju.setHasFixedSize(true)

        listBaju.addAll(getListBajuAdat())


        val intent = activity?.intent
        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent?.getParcelableExtra<DetailResponse>("responseBody", DetailResponse::class.java) as DetailResponse
        }else {
            @Suppress("DEPRECATION")
            (intent?.getParcelableExtra<DetailResponse>("responseBody"))
        }


        if (data != null) {
            getObjectByName(data.predictedLabel)
            binding.deskripsiAdat.text = data.detail.asalDaerah + "\n\n" + data.detail.detailSuku
            binding.bajuBajuadat.text = data.detail.namaBaju
            binding.deskripsiMotif.text = data.detail.bahanMotif
            binding.deskripsiPenggunaan.text = data.detail.penggunaan
        }

        expandableTextView.setAnimationDuration(750L)
        expandableTextView.setInterpolator(OvershootInterpolator())

        toggleText.setOnClickListener{
            if (expandableTextView.isExpanded){
                expandableTextView.collapse()
                toggleText.text = "Lihat Selengkapnya..."
            }else {
                expandableTextView.expand()
                toggleText.text = "Perkecil Deskripsi..."
            }
        }

        val sejarahExpandableTextView = binding.deskripsiAdat
        val sejarahToggleText = binding.lihatSelengkapnya

        sejarahExpandableTextView.setAnimationDuration(750L)
        sejarahExpandableTextView.setInterpolator(OvershootInterpolator())

        sejarahToggleText.setOnClickListener{
            if (sejarahExpandableTextView.isExpanded){
                sejarahExpandableTextView.collapse()
                sejarahToggleText.text = "Lihat Selengkapnya..."
            }else {
                sejarahExpandableTextView.expand()
                sejarahToggleText.text = "Perkecil Deskripsi..."
            }
        }

        showRecyclerListWisata()



    }

    fun getObjectByName(name: String): DataDetailDeteksi? {
        val imageList = ArrayList<SlideModel>()
        for (obj in dataDetail) {
            if (obj.predictedLabel == name) {

                var i = 0
                while (i < 3) {
                    imageList.add(SlideModel(obj.picture[i]))
                    i++
                }

                var l = 0
                while (l < 5){
                    listWisata.add(DataWisata(obj.tempatWisataString[l],obj.tempatWisataNamaString[l]))
//                    listBangunan.add(DataWisata(obj.bangunanBersejarah[l],obj.bangunanBersejaraNama[l]))
                    l++
                }

                var j = 0
                while (j < 4) {
                    listMakanan.add(DataWisata(obj.makananKhas[j], obj.makananKhasNama[j]))
                    listBangunan.add(DataWisata(obj.bangunanBersejarah[j],obj.bangunanBersejaraNama[j]))
                    j++
                }


                val imageSlider = binding.viewpager
                imageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)
                binding.bajuAdat.text = obj.name
                binding.lokasiDetail.text = obj.loc


            }
        }
        return null
    }

    private fun showRecyclerListWisata() {

        rvWisata.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listEventAdapter = WisataAdapter(listWisata)
        rvWisata.adapter = listEventAdapter

        rvMakanan.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listMakanAdapter = WisataAdapter(listMakanan)
        rvMakanan.adapter = listMakanAdapter


        rvBangunan.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val listBangunAdapter = WisataAdapter(listBangunan)
        rvBangunan.adapter = listBangunAdapter

        rvBaju.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        val listGameAdapter = ListBajuAdatAdapter(listBaju)
        rvBaju.adapter = listGameAdapter

    }

    private fun getListBajuAdat(): ArrayList<BajuAdat> {

        val dataPict = resources.obtainTypedArray(R.array.bajuadat_picture)
        val dataName = resources.getStringArray(R.array.bajuadat_name)
        val listGame = ArrayList<BajuAdat>()
        for (i in dataName.indices) {
            val game = BajuAdat(dataPict.getResourceId(i, -1), dataName[i])
            listGame.add(game)
        }
        return listGame
    }



    companion object {
        fun newInstance() : DetailDeteksiFragment {
            return DetailDeteksiFragment()
        }
    }
}