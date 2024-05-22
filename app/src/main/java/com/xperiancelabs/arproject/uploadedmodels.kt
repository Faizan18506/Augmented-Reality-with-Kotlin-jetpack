package com.xperiancelabs.arproject

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.storage.FirebaseStorage
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import java.io.File
import kotlin.math.max
import kotlin.math.min


@Composable
fun Uplodedmodels(navController: NavController) {
    val context = LocalContext.current
    var modelNode by remember { mutableStateOf<ArModelNode?>(null) }
  //  val uploadedModelsDirectory = context.filesDir.resolve("assets/uploaded_models")
    val uploadedModelsDirectory = File(context.filesDir, "assets/uploaded_models")
    if (!uploadedModelsDirectory.exists()) {
        uploadedModelsDirectory.mkdirs() // This will create the directory if it doesn't exist
    }
    val modelFiles = uploadedModelsDirectory.listFiles()?.map { it.absolutePath } ?: emptyList<String>()
    var currentModelIndex by remember { mutableStateOf(0) }

    // This will replace your sceneView
    val sceneView = remember { ArSceneView(context) }

    // This will replace your model loading logic
    LaunchedEffect(currentModelIndex) {
        modelNode?.let { sceneView.removeChild(it) }
        modelNode = ArModelNode().apply {
            loadModelGlbAsync(glbFileLocation = modelFiles[currentModelIndex]) {
                sceneView.planeRenderer.isVisible = true
            }
            onAnchorChanged = {
                // Handle anchor change
            }
        }
        sceneView.addChild(modelNode!!)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView({ sceneView }, modifier = Modifier.matchParentSize())

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        )  {
            Button(
                onClick = { currentModelIndex = (currentModelIndex - 1 + modelFiles.size) % modelFiles.size },
                colors = ButtonDefaults.buttonColors(containerColor = customButtonColor33),
                shape = customShape55,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Text("Previous", color = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            ExtendedFloatingActionButton(
                text = { Text("Place", color = Color.White) },
                onClick = { /* TODO: Implement model placement */ },
                icon = { Icon(Icons.Filled.Place, contentDescription = null) },
                containerColor = customFabColor44,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { currentModelIndex = (currentModelIndex + 1) % modelFiles.size },
                colors = ButtonDefaults.buttonColors(containerColor = customButtonColor33),
                shape = customShape55,
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            ) {
                Text("Next", color = Color.White)
            }
        }
    }
}
