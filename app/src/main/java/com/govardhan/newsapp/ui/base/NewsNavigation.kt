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
import com.govardhan.newsapp.ui.language.LanguageRoute
import com.govardhan.newsapp.ui.newssource.NewsSourceRoute
import com.govardhan.newsapp.ui.offlinearticle.TopHeadlineOfflineRoute
import com.govardhan.newsapp.ui.search.SearchRoute
import com.govardhan.newsapp.ui.topheadline.TopHeadlineRoute

sealed class Route(val name:String){
    object Home: Route("Home")
    object TopHeadline: Route("TopHeadline")
    object NewsSource: Route("NewsSource")
    object Country: Route("country")
    object Language: Route("Language")
    object Search: Route("Search")
    object TopHeadlineOffline: Route("TopHeadlineOffline")
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
            TopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context,it)
            })
        }

        composable(route = Route.NewsSource.name){
            NewsSourceRoute(onNewsClick = {
                openCustomChromeTab(context,it)
            })
        }

        composable(route = Route.Country.name){
            CountryRoute()
        }

        composable(route = Route.Language.name){
            LanguageRoute()
        }

        composable(route = Route.Search.name){
           SearchRoute(onNewsClick = {
               openCustomChromeTab(context,it)
           })
        }

        composable(route = Route.TopHeadlineOffline.name){
            TopHeadlineOfflineRoute(onNewsClick = {
                openCustomChromeTab(context,it)
            })
        }
    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}