package com.cursosandroidant.starrail.ui.presentation.materiales

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.rememberImagePainter
import com.cursosandroidant.starrail.data.model.Material
import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.ui.components.BottomNavigationBar
import com.cursosandroidant.starrail.ui.presentation.personaje.OneCharacterScreen
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

class OneMaterialScreen(private val materialJson: String) : Screen {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun Content() {
        val gson = remember { Gson() }
        val material = remember { gson.fromJson(materialJson, Material::class.java) }

        val viewModel: MaterialesViewModel = koinViewModel()



        val personajeList by viewModel.personajeLista.collectAsState()

        LaunchedEffect(material.nombre) {
            viewModel.fetchPersonajesConMaterial(material.nombre)
        }




        Scaffold(
            topBar = { /* Add your top bar here */ },
            content = { paddingValues ->
                CurrencyScreen(
                    material = material,
                    personajes = personajeList,
                    modifier = Modifier.padding(paddingValues)
                )
            },
            floatingActionButton = { /* Add your FAB here */ },
            bottomBar = { BottomNavigationBar(LocalNavigator.currentOrThrow) {} },
            containerColor = Color.Black // Set the background color of Scaffold
        )
    }

    @Composable
    fun CurrencyScreen(
        material: Material,
        personajes: List<Personaje>,
        modifier: Modifier = Modifier
    ) {
        val starCount = material.estrella
        val starText = "★".repeat(starCount)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Padding externo para que se vea el redondeo
            contentPadding = PaddingValues(bottom = 80.dp) // Espacio extra en la parte inferior si se necesita
        ) {
            item {
                // Tarjeta del material
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = material.nombre,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )

                        Divider(
                            color = Color.Gray,
                            thickness = 2.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Text(
                            text = material.descripcion,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = starText,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Yellow,
                                fontWeight = FontWeight.Bold
                            )
                            Image(
                                painter = rememberImagePainter(material.imageUrl),
                                contentDescription = "Imagen de ${material.nombre}",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray),
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center
                            )
                        }

                        Divider(
                            color = Color.Gray,
                            thickness = 2.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Text(
                            text = material.curiosidad,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Formas de obtención",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                material.obtencion?.forEach { texto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(8.dp), // Redondear cada tarjeta
                    ) {
                        Text(
                            text = texto,
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black
                        )
                    }
                }
            }

            // Mostrar personajes solo si hay personajes disponibles
            if (personajes.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Personajes que lo utilizan ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    personajes.forEach { item ->
                        CharacterCard(item)
                    }
                }
            }
        }
    }


}


@Composable
fun CharacterCard(personaje: Personaje) {
    val navigator = LocalNavigator.currentOrThrow
    Row(modifier = Modifier
        .clickable {

            navigator.push(OneCharacterScreen(Gson().toJson(personaje)))



        }) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .widthIn(max = 117.dp, min = 117.dp)
                .heightIn(max = 150.dp, min = 150.dp)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp)
                ), // Ajusta el ancho del Column
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(personaje.imageUrl),
                contentDescription = "Imagen de ${personaje.nombre}",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop

            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = personaje.nombre,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}