package com.example.littlelemon

import android.content.Context
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, context: Context){
    //If info in sharedprefs, startDes=Home, else startDes=Onboarding
    NavHost(navController = navController, startDestination = Onboarding.route){
        composable(Onboarding.route){
            Onboarding(navController, context)
        }
        composable(Home.route){
            Home(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }
    }
}