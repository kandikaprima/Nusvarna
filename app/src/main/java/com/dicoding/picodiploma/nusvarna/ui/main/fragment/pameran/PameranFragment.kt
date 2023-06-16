package com.dicoding.picodiploma.nusvarna.ui.main.fragment.pameran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.adapter.EventAdapter
import com.dicoding.picodiploma.nusvarna.adapter.ListBajuAdatAdapter
import com.dicoding.picodiploma.nusvarna.data.BajuAdat
import com.dicoding.picodiploma.nusvarna.data.EventCard
import com.dicoding.picodiploma.nusvarna.databinding.FragmentBerandaBinding
import com.dicoding.picodiploma.nusvarna.databinding.FragmentPameranBinding
import com.dicoding.picodiploma.nusvarna.response.ProfileResponse
import com.dicoding.picodiploma.nusvarna.utils.ViewModelFactory
import de.hdodenhof.circleimageview.CircleImageView

class PameranFragment : Fragment() {

    private lateinit var binding: FragmentPameranBinding

    private lateinit var rvEventPage: RecyclerView
    private val listEventPage = ArrayList<EventCard>()

    private lateinit var pameranViewModel: PameranViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPameranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pameranViewModel = ViewModelProvider(requireActivity(), ViewModelFactory.getInstance())[PameranViewModel::class.java]
        pameranViewModel.getLogin()

        pameranViewModel.profile.observe(viewLifecycleOwner){
            setUserData(it)
        }


        rvEventPage = binding.rvEventpage
        rvEventPage.setHasFixedSize(true)

        listEventPage.addAll(getListEventPage())

        showRecyclerList()

    }

    private fun getListEventPage(): ArrayList<EventCard> {

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

    private fun showRecyclerList() {

        rvEventPage.layoutManager = LinearLayoutManager(requireContext())
        val listEventAdapter = EventAdapter(listEventPage)
        rvEventPage.adapter = listEventAdapter

    }

    private fun setUserData(profile: ProfileResponse) {


        binding.profileUserName.text = profile.name
        binding.profileUserEmail.text = profile.email
        Glide.with(binding.profileUser).load(profile.profile_photo_url).into(binding.profileUser)
    }



    companion object {
        fun newInstance() : PameranFragment {
            return PameranFragment()
        }
    }
}