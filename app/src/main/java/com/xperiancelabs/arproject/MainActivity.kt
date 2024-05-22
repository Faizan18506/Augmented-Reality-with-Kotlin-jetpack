package com.xperiancelabs.arproject


import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat
import com.xperiancelabs.arproject.ui.theme.ARProjectTheme


import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.toArgb


import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            ARProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "Splash") {
                        composable("Splash") {
                            SplashScreen(navController = navController, context = this@MainActivity)
                        }
                        composable("Category") {
                            CategoryScreen(navController = navController)
                        }
                        composable("Onboarding") {
                            OnboardingScreen(navController = navController)
                        }
                        composable("FurnitureARScreen") {
                            FurnitureARScreen1(navController = navController)
                    }
                        composable("HouseDecorARScreen") {
                            HouseDecorARScreen1(navController = navController)
                        }
                        composable("FoodItemsARScreen") {
                            FoodItemsARScreen1(navController = navController)
                        }
                        composable("upload screen") {
                            UploadScreen(navController = navController)
                        }
                        composable("uploading screen") {
                            Uplodedmodels(navController = navController)
                        }
                }
            }
        }

    }

}}