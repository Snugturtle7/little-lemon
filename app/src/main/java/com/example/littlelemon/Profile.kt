package com.example.littlelemon

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

//navController: NavController (param)
//context: Context (param)
@Composable
fun Profile(navController: NavController, context: Context){
    val sharedPrefs = context.getSharedPreferences("PersonalInfo", MODE_PRIVATE)
    val firstName = sharedPrefs.getString("firstName", "error")
    val lastName = sharedPrefs.getString("lastName", "error")
    val email = sharedPrefs.getString("email", "error")

    fun onClickLogOut(){
        sharedPrefs.edit().clear().apply()
        navController.navigate(Onboarding.route)
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(vertical = 12.dp, horizontal = 10.dp)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .padding(vertical = 10.dp)
                .requiredSize(185.dp, 40.dp)
        )
        Spacer(Modifier.padding(vertical = 40.dp))
        Text(text = "Profile Information", fontWeight = FontWeight.Bold)
        Spacer(Modifier.padding(15.dp))
        Text(text="First Name:", fontSize = 12.sp, modifier = Modifier
            .align(Alignment.Start)
            .padding(vertical = 5.dp))
        OutlinedTextField(
            value=firstName!!,
            onValueChange = {},
            readOnly = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF333333),
                unfocusedTextColor = Color(0xFF333333),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Text(text="Last Name:", fontSize = 12.sp, modifier = Modifier
            .align(Alignment.Start)
            .padding(vertical = 5.dp))
        OutlinedTextField(
            value=lastName!!,
            onValueChange = {},
            readOnly = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF333333),
                unfocusedTextColor = Color(0xFF333333),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Text(text="Email:", fontSize = 12.sp, modifier = Modifier
            .align(Alignment.Start)
            .padding(vertical = 5.dp))
        OutlinedTextField(
            value=email!!,
            onValueChange = {},
            readOnly = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF333333),
                unfocusedTextColor = Color(0xFF333333),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Spacer(Modifier.padding(30.dp))
        Button(
            onClick = { onClickLogOut() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFACE14),
                contentColor = Color(0xFF333333)
            ),
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text="Log out", modifier = Modifier.padding(horizontal = 60.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfilePreview(){
//    LittleLemonTheme {
//        Profile()
//    }
//}