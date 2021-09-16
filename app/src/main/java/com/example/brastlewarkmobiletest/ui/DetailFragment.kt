package com.example.brastlewarkmobiletest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.brastlewarkmobiletest.common.MyApp
import com.example.brastlewarkmobiletest.common.Utils
import com.example.brastlewarkmobiletest.databinding.FragmentDetailBinding
import com.example.brastlewarkmobiletest.domain.Inhabitant

class DetailFragment(val inhabitant: Inhabitant) : Fragment() {

    private lateinit var _binding : FragmentDetailBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        }

        binding.textViewName.text = inhabitant.name
        binding.textViewAge.text = inhabitant.age.toString()
        binding.textViewFriends.text = inhabitant.friends.joinToString(",","","",-1)
        binding.textViewProfessions.text = inhabitant.professions.joinToString (",","","", -1)

        Glide.with(MyApp.applicationContext()).load(Utils().generateGlideUrl(inhabitant.thumbnail)).into(binding.ivInhabitant)
    }
}