package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Onboarding(){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier.padding(vertical = 10.dp).requiredSize(185.dp, 40.dp)
        )
        Surface(color = Color(0xff495e57), modifier = Modifier.fillMaxWidth().padding(start=10.dp)){
            Text(
                text="Let's get to know you",
                color=Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier= Modifier.padding(vertical = 20.dp)
            )
        }
        Text(
            text="Personal Information",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 10.dp, vertical = 15.dp)
        )
        Text(
            text="First name",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        OutlinedTextField(
            value = firstName,
            onValueChange = {firstName=it},
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text="Last name",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        OutlinedTextField(value = lastName, onValueChange = {lastName=it})
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text="Email",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        OutlinedTextField(value = email, onValueChange = {email=it})
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFACE14),
                contentColor = Color(0xFF333333)
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
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