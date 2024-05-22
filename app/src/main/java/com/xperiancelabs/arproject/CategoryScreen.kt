package com.xperiancelabs.arproject

import androidx.compose.ui.unit.dp
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.util.Locale.Category

val customButtonColors = Color(0xFF000000)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CategoryScreen(navController: NavHostController) {
    // Define a data class to hold category information
    data class Category(val name: String, val imageRes: Int)

    // Create a list of categories with their corresponding images
    val categories = listOf(

        Category("Houses", R.drawable.housess),
        Category("Food Items", R.drawable.burger),
        Category("Furniture",  R.drawable.sofa),
        Category("House Decor", R.drawable.decor),
        Category("Uploaded Models", R.drawable.downld),
        Category("Upload Your Models", R.drawable.upload)


    )

    val houseImage = painterResource(id = R.drawable.headimage)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Select a Category",
                        color = Color.Black, // Set text color to black
                        fontWeight = FontWeight.Bold // Set font weight to bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (isSystemInDarkTheme()) {
                        // Custom color for dark theme
                        Color(0xFF794044)
                    } else {
                        // Default color for light theme
                        MaterialTheme.colorScheme.surface
                    }
                )
            )

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                    .background(Color(0xFF58020e)) // Oceanic Blue
        ) {
            Image(
                painter = houseImage,
                contentDescription = "House",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Start)
            )
            Text(
                text = "Augmented Reality in Real Estate",
                modifier = Modifier
                    .align(Alignment.Start)
                   // .background(Color(0xFFf3ca20))
                    .padding(start = 2.dp, bottom = 2.dp),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 24.sp, // Increase font size
                    fontWeight = FontWeight.Bold // Make it bold
                )
            )
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF58020e))

                            .height(120.dp)
                            .clickable {
                                when (category.name) {
                                    "Houses" -> navController.navigate("Onboarding")
                                    "Furniture" -> navController.navigate("FurnitureARScreen")
                                    "House Decor" -> navController.navigate("HouseDecorARScreen")
                                    "Food Items" -> navController.navigate("FoodItemsARScreen")
                                    "Uploaded Models" -> navController.navigate("uploading screen")
                                    "Upload Your Models" -> navController.navigate("upload screen")

                                    else -> navController.navigate("Onboarding")
                                }
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        shape = if (category.name == "Upload Your Models") RoundedCornerShape(100.dp) else RoundedCornerShape(60.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (category.name == "Upload Your Models") Color.Black else Color(0xFF794044),
                            contentColor = if (category.name == "Upload Your Models") Color.White else Color.Black
                        )

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = category.imageRes),
                                contentDescription = "${category.name} image",
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = category.name,
                                style = TextStyle(color = Color.White, fontSize = 20.sp)
                            )
                        }
                    }
                }
            }
        }
    }
}





