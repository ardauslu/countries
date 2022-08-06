package com.example.countries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.countries.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.adapter.CountryListAdapter
import com.example.countries.data.Data
import com.example.countries.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: CountryListAdapter
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        replaceFragment(Home())
        setContentView(binding.root)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {


            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.fav -> replaceFragment(Favorites())
                else ->{
                }
            }
            true
        }

        initRecyclerView()
        initViewModel()


    }

    private fun initRecyclerView() {
        countryListRecyclerview.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = CountryListAdapter(this)
        countryListRecyclerview.adapter =recyclerAdapter
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()

    }

     private fun initViewModel() {
        val viewModel:MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null) {
                recyclerAdapter.setCountryList(it.data)
                recyclerAdapter.notifyDataSetChanged()
                val intent = Intent(this, CountryDetailActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }
}