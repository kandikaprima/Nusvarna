package com.dicoding.picodiploma.nusvarna.ui.main.fragment.evarna

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.adapter.EventAdapter
import com.dicoding.picodiploma.nusvarna.adapter.ListEvarnaAdapter
import com.dicoding.picodiploma.nusvarna.data.Evarna
import com.dicoding.picodiploma.nusvarna.data.EventCard
import com.dicoding.picodiploma.nusvarna.databinding.FragmentEvarnaBinding
import com.dicoding.picodiploma.nusvarna.databinding.FragmentPameranBinding
import com.dicoding.picodiploma.nusvarna.ui.main.fragment.pameran.PameranFragment


class EvarnaFragment : Fragment() {

    private lateinit var binding: FragmentEvarnaBinding

    private lateinit var rvEvarna: RecyclerView
    private val listEvarna = ArrayList<Evarna>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEvarnaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvEvarna = binding.rvEvarnapage
        rvEvarna.setHasFixedSize(true)

        listEvarna.addAll(getListEvarna())

        showRecyclerList()

    }

    private fun getListEvarna(): ArrayList<Evarna> {

        val dataPict = resources.obtainTypedArray(R.array.evarna_picture)
        val dataSewa = resources.getStringArray(R.array.evarna_disewakan)
        val dataName = resources.getStringArray(R.array.evarna_name)
        val dataPrice = resources.getStringArray(R.array.evarna_harga)
        val dataLoc = resources.getStringArray(R.array.evarna_loc)
        val dataRating = resources.getStringArray(R.array.evarna_rating)
        val listGame = ArrayList<Evarna>()
        for (i in dataName.indices) {
            val game = Evarna(dataPict.getResourceId(i, -1),dataSewa[i], dataName[i],dataPrice[i], dataLoc[i], dataRating[i])
            listGame.add(game)
        }
        return listGame
    }

    private fun showRecyclerList() {

        rvEvarna.layoutManager = GridLayoutManager(requireContext(), 3, LinearLayoutManager.VERTICAL, false)
        val listEventAdapter = ListEvarnaAdapter(listEvarna)
        rvEvarna.adapter = listEventAdapter

    }


    companion object {
        fun newInstance() : EvarnaFragment {
            return EvarnaFragment()
        }
    }
}