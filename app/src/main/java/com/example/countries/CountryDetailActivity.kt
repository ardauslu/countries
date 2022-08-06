package com.example.countries

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.countries.databinding.ActivityCountryDetailBinding
import kotlinx.android.synthetic.main.activity_country_detail.*
import kotlinx.android.synthetic.main.activity_country_detail.view.*


class CountryDetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityCountryDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        var intent = intent
        var countryNameTextView = findViewById<TextView>(R.id.country_name)
        var countryName = intent.getStringExtra("name")
        countryNameTextView.text = countryName

    }
}

