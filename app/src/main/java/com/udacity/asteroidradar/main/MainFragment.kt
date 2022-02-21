package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.AsteroidClickListener
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.RecyclerViewAdapter
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        adapter = RecyclerViewAdapter(AsteroidClickListener{ asteroid ->
            viewModel.onAsteroidClicked(asteroid)
        })

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        binding.recyclerviewAsteroid.adapter = adapter

        viewModel.navigateToAsteroid.observe(viewLifecycleOwner, Observer {
            asteroid ->
            asteroid?.let {
                this.findNavController().navigate(
                    MainFragmentDirections.actionShowDetail(it)
                )
                viewModel.onDetailAsteroidNavigated()
            }
        })

        viewModel.response.observe(viewLifecycleOwner, Observer {
            asteroid -> adapter.setData(asteroid)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.show_rent_menu -> viewModel.getTodayAsteroids()
            R.id.show_all_menu -> viewModel.getAllAsteroids()
            R.id.show_buy_menu -> viewModel.getAllSavedAsteroids()

        }
        return true
    }
}
