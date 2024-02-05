package com.govardhan.newsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.govardhan.newsapp.databinding.ActivityMainBinding
import com.govardhan.newsapp.ui.NewsSource.NewsSourceActivity
import com.govardhan.newsapp.ui.country.CountryActivity
import com.govardhan.newsapp.ui.topheadline.TopHeadlineActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topHeadline.setOnClickListener {
           val intent = Intent(this, TopHeadlineActivity::class.java)
            startActivity(intent)
        }

        binding.newsSources.setOnClickListener {
            val intent = Intent(this, NewsSourceActivity::class.java)
            startActivity(intent)
        }

        binding.countries.setOnClickListener{
            val intent = Intent(this, CountryActivity::class.java)
            startActivity(intent)
        }

    }
}