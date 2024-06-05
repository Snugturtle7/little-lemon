package com.example.littlelemon

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, context: Context, db: MenuDatabase){
    //If info in sharedprefs, startDes=Home, else startDes=Onboarding
    var start = Onboarding.route
    val sharedPrefs = context.getSharedPreferences("PersonalInfo", MODE_PRIVATE)
    if (sharedPrefs.contains("firstName") &&
        sharedPrefs.contains("lastName") &&
        sharedPrefs.contains("email")
    ){
        start = Home.route
    }

    NavHost(navController = navController, startDestination = Home.route){
        composable(Onboarding.route){
            Onboarding(navController, context)
        }
        composable(Home.route){
            Home(navController, db)
        }
        composable(Profile.route){
            Profile(navController, context)
        }
    }
}