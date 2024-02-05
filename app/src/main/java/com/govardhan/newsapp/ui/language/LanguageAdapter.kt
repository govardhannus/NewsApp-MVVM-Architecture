package com.govardhan.newsapp.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.govardhan.newsapp.data.model.Country
import com.govardhan.newsapp.data.model.Language
import com.govardhan.newsapp.databinding.NewsSourceItemLayoutBinding

class LanguageAdapter(private val languageList: ArrayList<Language>) :RecyclerView.Adapter<LanguageAdapter.DataViewModel>(){

    class DataViewModel(private val binding: NewsSourceItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root){

        fun bind(language: Language){
            binding.newsSourceButton.text = language.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewModel (
        NewsSourceItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun getItemCount(): Int = languageList.size

    override fun onBindViewHolder(holder: DataViewModel, position: Int) = holder.bind(languageList[position])

    fun addData(list :List<Language>){
        languageList.addAll(list)
    }

}