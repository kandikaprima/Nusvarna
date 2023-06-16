package com.dicoding.picodiploma.nusvarna.ui.main.fragment.beranda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.adapter.EventAdapter
import com.dicoding.picodiploma.nusvarna.adapter.ListBajuAdatAdapter
import com.dicoding.picodiploma.nusvarna.adapter.LoopingPagerAdapter
import com.dicoding.picodiploma.nusvarna.data.BajuAdat
import com.dicoding.picodiploma.nusvarna.data.EventCard
import com.dicoding.picodiploma.nusvarna.databinding.FragmentBerandaBinding


class BerandaFragment : Fragment() {

    private lateinit var binding: FragmentBerandaBinding

    private val list = ArrayList<EventCard>()

    private lateinit var rvBajuAdat: RecyclerView
    private val listBajuAdat = ArrayList<BajuAdat>()

    private lateinit var rvEventMingguIni: RecyclerView
    private val listEventMingguIni = ArrayList<EventCard>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBerandaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()

        getListEvent(list)
        imageList.add(SlideModel("https://i.ibb.co/jZdCcZ9/Event-Card.png"))
        imageList.add(SlideModel("https://i.ibb.co/Lh9RMp0/Group-10.png"))
        imageList.add(SlideModel("https://i.ibb.co/pX7Km8W/Group-11.png"))
        imageList.add(SlideModel("https://i.ibb.co/XCCKGPL/Group-9.png"))

        val imageSlider = binding.viewpager
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)

        rvBajuAdat = binding.rvBajuadat
        rvBajuAdat.setHasFixedSize(true)

        listBajuAdat.addAll(getListBajuAdat())

        rvEventMingguIni = binding.rvEventMingguIni
        rvEventMingguIni.setHasFixedSize(true)

        listEventMingguIni.addAll(getListEventMingguIni())

        showRecyclerList()


    }

    private fun getListEvent(list: ArrayList<EventCard>): ArrayList<EventCard> {

        val dataPict = resources.obtainTypedArray(R.array.eventcard_picture)
        val dataName = resources.getStringArray(R.array.eventcard_name)
        val dataLinkedin = resources.getStringArray(R.array.eventcard_loc)
        for (i in dataName.indices) {
            val game = EventCard(dataPict.getResourceId(i, -1), dataName[i], dataLinkedin[i])
            list.add(game)
        }
        return list
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

    private fun showRecyclerList() {
        rvBajuAdat.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        val listGameAdapter = ListBajuAdatAdapter(listBajuAdat)
        rvBajuAdat.adapter = listGameAdapter

        rvEventMingguIni.layoutManager = LinearLayoutManager(requireContext())
        val listEventAdapter = EventAdapter(listEventMingguIni)
        rvEventMingguIni.adapter = listEventAdapter

    }

    private fun getListEventMingguIni(): ArrayList<EventCard> {

        val dataPict = resources.obtainTypedArray(R.array.eventmingguini_picture)
        val dataName = resources.getStringArray(R.array.eventmingguini_name)
        val dataLoc = resources.getStringArray(R.array.eventmingguini_loc)
        val listGame = ArrayList<EventCard>()
        for (i in dataName.indices) {
            val game = EventCard(dataPict.getResourceId(i, -1), dataName[i], dataLoc[i])
            listGame.add(game)
        }
        return listGame
    }





    companion object {
        fun newInstance() : BerandaFragment {
            return BerandaFragment()
        }
    }


}