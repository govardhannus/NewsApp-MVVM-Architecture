package com.govardhan.newsapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.govardhan.newsapp.ui.base.NewsNavHost
import com.govardhan.newsapp.ui.theme.NewsAppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            NewsAppTheme {
                NewsNavHost()
            }
        }
    }
}