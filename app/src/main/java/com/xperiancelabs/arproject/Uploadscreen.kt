package com.xperiancelabs.arproject

import android.content.ActivityNotFoundException
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

@Composable
fun UploadScreen(navController: NavHostController) {
    var selectedFile: Uri? by remember { mutableStateOf(null) }
    var fileSaveMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        selectedFile = uri
    }

    Column {
        Button(onClick = {
            try {
                launcher.launch("*/*")
            } catch (e: ActivityNotFoundException) {
                fileSaveMessage = "Error: Activity not found."
            }
        }) {
            Text("Select File")
        }

        selectedFile?.let { uri ->
            Button(onClick = {
                val inputStream = context.contentResolver.openInputStream(uri)
                val directory = context.filesDir.resolve("assets/uploaded_models")
                if (!directory.exists()) {
                    directory.mkdirs()
                }
                val file = directory.resolve(uri.lastPathSegment ?: "default_name")

                inputStream?.use { input ->
                    file.outputStream().use { fileOut ->
                        input.copyTo(fileOut)
                        fileSaveMessage = "File saved to local storage: ${file.absolutePath}"
                    }
                }
            }) {
                Text("Save Locally")
            }
        }

        // Display the file save message
        if (fileSaveMessage.isNotEmpty()) {
            Text(fileSaveMessage)
        }
    }
}
