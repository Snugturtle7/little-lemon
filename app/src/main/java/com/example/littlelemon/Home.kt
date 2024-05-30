package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Text


@Composable
fun Home(navController: NavController){
    Column {
        Text(text="YOU MADE IT!")
    }
}