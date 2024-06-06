package com.example.littlelemon

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.theme.littleLemonBody
import com.example.littlelemon.ui.theme.littleLemonHeader

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Home(navController: NavController, db: MenuDatabase){
    val menuItems = db.menuItemsDao().getAll().observeAsState()
    var searchPhrase by remember{ mutableStateOf("") }
    var filterCategory by remember { mutableStateOf(FilterCategory.NONE) }
    val listState = rememberLazyListState()
    Column {
        Row (horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Spacer(Modifier.padding(20.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .requiredSize(185.dp, 40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .padding(10.dp)
                    .requiredSize(40.dp)
                    .clickable { navController.navigate(Profile.route) }
            )
        }
        Surface(color = Color(0xFF495E57), modifier = Modifier.fillMaxWidth()){
            Column (modifier = Modifier.padding(15.dp)){
                Text(
                    text="Little Lemon",
                    color = Color(0xFFF4CE14),
                    fontSize = 42.sp,
                    fontFamily = littleLemonHeader
                )
                Row () {
                    Column(modifier = Modifier.weight(1F)) {
                        Text(
                            text="Chicago",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontFamily = littleLemonHeader
                        )
                        Spacer(Modifier.padding(10.dp))
                        Text(
                            text="We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                            color=Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .requiredSize(100.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                OutlinedTextField(
                    value = searchPhrase,
                    onValueChange = {searchPhrase=it},
                    placeholder = {Text(text="Enter search phrase")},
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xFF333333),
                        unfocusedTextColor = Color(0xFF333333),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search")}
                )
            }
        }
        Text(
            text="ORDER FOR DELIVERY!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(15.dp),
            fontFamily = littleLemonBody
        )
        Row (horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { filterCategory = setFilterCategory(FilterCategory.STARTERS, filterCategory) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFACE14),
                    contentColor = Color(0xFF333333)
                )
            ) {
                Text(text="Starters", fontSize = 12.sp)
            }
            Button(
                onClick = { filterCategory = setFilterCategory(FilterCategory.MAINS, filterCategory) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFACE14),
                    contentColor = Color(0xFF333333)
                )
            ) {
                Text(text="Mains", fontSize = 10.sp)
            }
            Button(onClick = { filterCategory = setFilterCategory(FilterCategory.DESSERTS, filterCategory) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFACE14),
                    contentColor = Color(0xFF333333)
                )
            ) {
                Text(text="Desserts", fontSize = 10.sp)
            }
            Button(onClick = { filterCategory = setFilterCategory(FilterCategory.DRINKS, filterCategory) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFACE14),
                    contentColor = Color(0xFF333333)
                )
            ) {
                Text(text="Drinks", fontSize = 10.sp)
            }
        }

        var filtered: List<MenuItem>? = null
        if (filterCategory != FilterCategory.NONE){
            filtered = menuItems.value?.filter { it.category.equals(filterCategory.name, ignoreCase = true)}
        }
        if (searchPhrase != "" && filtered == null){
            filtered = menuItems.value?.filter { it.title.contains(searchPhrase, ignoreCase = true) }
            filtered?.let { MenuItems(it, listState) }
        } else if (searchPhrase != ""){
            filtered = filtered?.filter { it.title.contains(searchPhrase, ignoreCase = true) }
            filtered?.let { MenuItems(it, listState) }
        } else if (filtered != null){
            filtered?.let { MenuItems(it, listState) }
        }
        else {
            menuItems.value?.let { MenuItems(it, listState) }
        }



    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItems(items: List<MenuItem>, listState: LazyListState){
    LazyColumn (state=listState, modifier = Modifier.padding(horizontal = 10.dp)){
        items(items) {item ->
            UIMenuItem(item = item)
        }
    }
//    Column {
//        items.forEach { UIMenuItem(item = it) }
//    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun UIMenuItem(item: MenuItem){
    Row (modifier = Modifier.padding(vertical = 8.dp)){
        Column(modifier = Modifier.weight(1F)) {
            Text(text=item.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, fontFamily = littleLemonBody, modifier = Modifier.padding(vertical = 8.dp))
            Text(text=item.description, fontSize = 12.sp, fontFamily = littleLemonBody)
            Text(text="$"+item.price+".00", fontSize = 16.sp, fontFamily = littleLemonBody)
        }
        GlideImage(model = item.image, contentDescription = null, modifier = Modifier.requiredSize(100.dp))
    }
    
}

private fun setFilterCategory(setTo: FilterCategory, current: FilterCategory): FilterCategory{
    return if (setTo == current) FilterCategory.NONE
    else setTo
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview(){
//    LittleLemonTheme {
//        Home()
//    }
//}