package com.example.countries.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.CountryDetailActivity
import com.example.countries.data.Data
import kotlinx.android.synthetic.main.activity_country_detail.view.*
import kotlinx.android.synthetic.main.activity_country_detail.*
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.circleCrop
import com.example.countries.R
import kotlinx.android.synthetic.main.country_list_row.view.*
import java.net.URL


class CountryListAdapter(val activity: Activity): RecyclerView.Adapter<CountryListAdapter.MyViewHolder>() {

    private var countryList: List<Data>? = null

    fun setCountryList(countryList: List<Data>?) {
        this.countryList = countryList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_list_row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryListAdapter.MyViewHolder, position: Int) {
        holder.bind(countryList?.get(position)!!, activity)
    }
    override fun getItemCount(): Int {
        return if(countryList == null) 0
        else countryList?.size!!
    }

    class MyViewHolder(private var view: View): RecyclerView.ViewHolder(view){

        private val tvName: TextView = view.findViewById<TextView>(R.id.tvName)
        private val tvImage: ImageView = view.findViewById<ImageView>(R.id.tvImage)
        fun bind(data: Data, activity: Activity) {
            tvName.text = data.name
            Glide.with(activity)
                .load("https://upload.wikimedia.org/wikipedia/commons/a/a4/Flag_of_the_United_States.svg")
                .into(tvImage)


            view.setOnClickListener{
                var intent = Intent(view.context, CountryDetailActivity::class.java)
                intent.putExtra("name",tvName.text)
                view.context.startActivity(intent)
            }
        }
    }
}