package com.example.areader.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.areader.screens.ReaderSplashScreen
import com.example.areader.screens.details.BookDetailsScreen
import com.example.areader.screens.home.HomeScreen
import com.example.areader.screens.login.LoginScreen
import com.example.areader.screens.search.SearchScreen
import com.example.areader.screens.stats.StatsScreen
import com.example.areader.screens.update.UpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ReaderScreens.SplashScreen.name
    ) {
        composable(ReaderScreens.SplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }

        composable(ReaderScreens.DetailScreen.name){
            BookDetailsScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderHomeScreen.name){
            HomeScreen(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name){
            StatsScreen(navController = navController)
        }

        composable(ReaderScreens.UpdateScreen.name){
            UpdateScreen(navController = navController)
        }
    }
}