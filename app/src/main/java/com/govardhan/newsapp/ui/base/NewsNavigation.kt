package com.govardhan.newsapp.ui.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.govardhan.newsapp.ui.country.CountryRoute
import com.govardhan.newsapp.ui.home.HomeRoute
import com.govardhan.newsapp.ui.newssource.NewsSourceRoute
import com.govardhan.newsapp.ui.topheadline.TopHeadlineRoute

sealed class Route(val name:String){
    object Home: Route("Home")
    object TopHeadline: Route("TopHeadline")
    object NewsSource: Route("NewsSource")

    object Country: Route("country")
}
@Composable
fun NewsNavHost(){
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Route.Home.name){
        composable(route = Route.Home.name){
            HomeRoute(navController)
        }
        composable(route = Route.TopHeadline.name){
            TopHeadlineRoute(onNewsclick = {
                openCustomChromeTab(context,it)
            })
        }

        composable(route = Route.NewsSource.name){
            NewsSourceRoute(onNewsclick = {
                openCustomChromeTab(context,it)
            })
        }

        composable(route = Route.Country.name){
            CountryRoute()
        }
    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}