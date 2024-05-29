package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Onboarding(){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }



    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Surface(color = Color(0xff495e57)){
            Text(
                text="Let's get to know you",
                color=Color.White,
                fontSize = 10.sp,
                modifier= Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }
        Text(
            text="Personal Information",
            fontSize = 8.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 5.dp, vertical = 15.dp)
        )
        Text(
            text="First name",
            fontSize = 6.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 5.dp, vertical = 2.dp)
        )
        OutlinedTextField(value = firstName, onValueChange = {firstName=it})
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text="Last name",
            fontSize = 6.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 5.dp, vertical = 2.dp)
        )
        OutlinedTextField(value = lastName, onValueChange = {lastName=it})
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text="Email",
            fontSize = 6.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 5.dp, vertical = 2.dp)
        )
        OutlinedTextField(value = email, onValueChange = {email=it})
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text="Register", modifier = Modifier.padding(horizontal = 60.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    LittleLemonTheme {
        Onboarding()
    }
}