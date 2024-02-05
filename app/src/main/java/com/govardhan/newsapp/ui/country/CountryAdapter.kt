package com.govardhan.newsapp.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.govardhan.newsapp.data.model.Country
import com.govardhan.newsapp.databinding.NewsSourceItemLayoutBinding

class CountryAdapter(private val countryList:ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.DataViewModel>() {

    class DataViewModel(private val binding: NewsSourceItemLayoutBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(country:Country){
            binding.newsSourceButton.text = country.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= DataViewModel (
        NewsSourceItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DataViewModel, position: Int) = holder.bind(countryList[position])

    override fun getItemCount(): Int = countryList.size

    fun addData(list :List<Country>){
        countryList.addAll(list)
    }

}