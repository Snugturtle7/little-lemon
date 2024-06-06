package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val client: HttpClient = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType= ContentType("text", "plain"))
        }
    }
    val database by lazy { MenuDatabase.getDatabase(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val response = fetchMenu()
            val entities = response.menu.map {
                MenuItem(
                    it.id,
                    it.title,
                    it.description,
                    it.price,
                    it.image,
                    it.category
                )
            }
            database.menuItemsDao().insertAll(entities)
        }
        setContent {
            LittleLemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val navController = rememberNavController()
                    Navigation(navController, this, database)
                }
            }
        }
    }

    private suspend fun fetchMenu(): MenuNetwork{
        return client
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LittleLemonTheme {
//        Onboarding()
//    }
//}