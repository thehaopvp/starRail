package com.cursosandroidant.starrail.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import com.cursosandroidant.starrail.data.model.AscensionLevel
import com.cursosandroidant.starrail.ui.presentation.materiales.OneMaterialScreen

@Composable
fun <T> CustomSlider(ascension: List<T>?) {


    val levels = ascension ?: return

    var currentLevel by remember { mutableStateOf(1) }

    val maxLevel = levels.size

    val level = levels.getOrNull(currentLevel-1)

    var materialsForCurrentLevel = ""
    var materialsForLevel = ""


    if (level is AscensionLevel) {

        materialsForCurrentLevel = level.materials?.joinToString(", ") {
            "${it.material_necesario} ${it.id}"
        } ?: "No disponible"

        materialsForLevel =   level.level
    } else {

        materialsForCurrentLevel = "No disponible"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (level is AscensionLevel) {
            Text(
                text = "Nivel Actual: $materialsForLevel",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {

                level.materials?.forEach { material ->

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        val material_img = material.material_img

                        // Imagen
                        Image(
                            painter = rememberImagePainter(material_img),
                            contentDescription = "Imagen del material ${material.id}",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                                .padding(4.dp).clickable {

                                },
                            contentScale = ContentScale.Crop
                        )

                        // Texto justo en la parte inferior de la imagen
                        Text(
                            text = material.material_necesario ?: "Desconocido",  // Usa el id o un texto por defecto
                            style = MaterialTheme.typography.bodySmall,  // Puedes personalizar el estilo
                            color = Color.White,
                            modifier = Modifier
                                .padding(bottom = 4.dp)  // Espaciado entre la imagen y el texto
                        )

                    }

                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Slider(
            value = currentLevel.toFloat(), onValueChange = { newValue ->
                currentLevel = newValue.toInt()
            }, valueRange = 1f..maxLevel.toFloat(), // Usar el número de niveles como el rango
            steps = maxLevel - 2, // Para que haya un paso entre cada nivel
            modifier = Modifier.fillMaxWidth()
        )
    }
}