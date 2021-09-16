package com.example.brastlewarkmobiletest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.brastlewarkmobiletest.R
import com.example.brastlewarkmobiletest.adapters.InhabitantsAdapter
import com.example.brastlewarkmobiletest.databinding.ActivityMainBinding
import com.example.brastlewarkmobiletest.network.Service
import com.example.brastlewarkmobiletest.domain.Inhabitant
import com.example.brastlewarkmobiletest.repository.InhabitantsRepository
import com.example.brastlewarkmobiletest.viewmodels.HomeViewModel
import com.example.brastlewarkmobiletest.viewmodels.HomeViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter : InhabitantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

        viewModel.getAllInhabitants()
    }

    private fun setUpView() {
        val retrofitService = Service.getInstance()
        val inhabitantsRepository = InhabitantsRepository(retrofitService)

        viewModel = ViewModelProvider(this, HomeViewModelFactory(inhabitantsRepository)).get(
            HomeViewModel::class.java)

        viewModel.inhabitantsList.observe(this, { result ->
            adapter = InhabitantsAdapter(result, onInhabitantSelected)

            result.forEach {
                viewModel.insertInhabitant(it)
            }

            binding.inhabitantsRecyclerView.adapter = adapter
            binding.inhabitantsRecyclerView.setHasFixedSize(true)
        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, {
            if(it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(textFilter: String?): Boolean {
                adapter.filter.filter(textFilter)
                return false
            }

        })
    }

    private val onInhabitantSelected : (inhabitant: Inhabitant) -> Unit = { inhabitant ->
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, DetailFragment(inhabitant), "DETAIL_FRAGMENT").commit()
    }
}