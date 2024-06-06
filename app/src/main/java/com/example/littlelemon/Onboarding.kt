package com.example.littlelemon

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.ui.theme.littleLemonHeader


@Composable
fun Onboarding(navController: NavController, context: Context){
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var failedValidation by remember { mutableStateOf(false) }

    fun validInputs(): Boolean{
        return !(firstName.isBlank() || lastName.isBlank() || email.isBlank())
    }

    fun saveInputs(){
        val sharedPrefs = context.getSharedPreferences("PersonalInfo", MODE_PRIVATE)
        sharedPrefs.edit().putString("firstName", firstName).apply()
        sharedPrefs.edit().putString("lastName", lastName).apply()
        sharedPrefs.edit().putString("email", email).apply()
    }

    fun onClickRegister(){
        if(validInputs()){
            failedValidation = false
            saveInputs()
            navController.navigate(Home.route)
        } else {
            failedValidation = true
        }
    }

    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .padding(vertical = 10.dp)
                .requiredSize(185.dp, 40.dp)
        )
        Surface(color = Color(0xff495e57), modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)){
            Text(
                text="Let's get to know you",
                color=Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier= Modifier.padding(vertical = 20.dp),
                fontFamily = littleLemonHeader
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
        if(failedValidation){
            Text(
                text="Registration unsuccessful. Please enter all data.",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
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
            modifier = Modifier.padding(horizontal = 10.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF333333),
                unfocusedTextColor = Color(0xFF333333),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text="Last name",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        OutlinedTextField(
            value = lastName,
            onValueChange = {lastName=it},
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF333333),
                unfocusedTextColor = Color(0xFF333333),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text="Email",
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF333333),
                unfocusedTextColor = Color(0xFF333333),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Email)
        )
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Button(
            onClick = { onClickRegister() },
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

//@Preview(showBackground = true)
//@Composable
//fun OnboardingPreview(){
//    LittleLemonTheme {
//        Onboarding()
//    }
//}