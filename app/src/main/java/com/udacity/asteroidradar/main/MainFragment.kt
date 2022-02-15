package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.AsteroidClickListener
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.RecyclerViewAdapter
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val adapter = RecyclerViewAdapter(AsteroidClickListener{ asteroid ->
            viewModel.onAsteroidClicked(asteroid)
        })

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        binding.recyclerviewAsteroid.adapter = adapter
        val time = Asteroid(0,"Leroy","Leroy",0.2,0.2,0.2,2.2,false)
        val time2 = Asteroid(0,"Leroy","Leroy",0.2,0.2,0.2,2.2,true)
        val times = listOf(time,time2)
        adapter.setData(times)

        viewModel.navigateToAsteroid.observe(viewLifecycleOwner, Observer {
            asteroid ->
            asteroid?.let {
                this.findNavController().navigate(
                    MainFragmentDirections.actionShowDetail(it)
                )
                viewModel.onDetailAsteroidNavigated()
            }
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
