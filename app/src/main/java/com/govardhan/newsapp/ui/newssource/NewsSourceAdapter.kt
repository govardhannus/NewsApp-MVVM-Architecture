package com.govardhan.newsapp.ui.newssource

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.govardhan.newsapp.data.model.Source
import com.govardhan.newsapp.databinding.NewsSourceItemLayoutBinding

class NewsSourceAdapter (private val sourceList: ArrayList<Source>) : RecyclerView.Adapter<NewsSourceAdapter.DataviewHolder>(){

    class DataviewHolder (private val binding: NewsSourceItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root){
        fun bind(source: Source){
            binding.newsSourceButton.text = source.name
            binding.newsSourceButton.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(source.url))
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
       DataviewHolder (
           NewsSourceItemLayoutBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )

    override fun onBindViewHolder(holder: NewsSourceAdapter.DataviewHolder, position: Int) = holder.bind(sourceList[position])
    override fun getItemCount(): Int = sourceList.size

    fun addData(list: List<Source>) {
        sourceList.addAll(list)
    }
}