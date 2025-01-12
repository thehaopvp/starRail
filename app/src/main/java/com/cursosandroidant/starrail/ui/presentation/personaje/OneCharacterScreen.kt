package com.cursosandroidant.starrail.ui.presentation.personaje

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.rememberImagePainter
import com.cursosandroidant.starrail.data.model.Habilidad
import com.cursosandroidant.starrail.data.model.Habilidades
import com.cursosandroidant.starrail.data.model.Mejora
import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.ui.components.BottomNavigationBar
import com.cursosandroidant.starrail.ui.components.CustomSlider
import com.google.gson.Gson

class OneCharacterScreen(private val personajeJson: String) : Screen {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun Content() {

        val gson = remember { Gson() }
        val personaje = remember { gson.fromJson(personajeJson, Personaje::class.java) }

        Scaffold(
            topBar = { /* Add your top bar here */ },
            content = { paddingValues ->
                CurrencyScreen(personaje = personaje, modifier = Modifier.padding(paddingValues))
            },
            floatingActionButton = { /* Add your FAB here */ },
            bottomBar = { BottomNavigationBar(LocalNavigator.currentOrThrow) {} },
            containerColor = Color.Black // Set the background color of Scaffold
        )
    }


    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun CurrencyScreen(
        personaje: Personaje,
        modifier: Modifier = Modifier
    ) {
        val starCount = personaje.estrella
        val starText = "★".repeat(starCount)
        var sliderValue by remember { mutableStateOf(0f) }
        val expandedStateEidolon = remember { mutableStateOf(false) }
        val expandedStateHabilidad = remember { mutableStateOf(false) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp), // Padding externo para que se vea el redondeo
            contentPadding = PaddingValues(bottom = 80.dp) // Espacio extra en la parte inferior si se necesita
        ) {
            item {
                // Tarjeta del material
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .height(350.dp).background(color = Color.Gray),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Nombre:  ",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = personaje.nombre,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.Black
                            )
                        }


                        TabRowDefaults.Divider(
                            color = Color.Gray,
                            thickness = 2.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Descripción:  ",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = personaje.descripcion,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.Black
                            )
                        }

                        TabRowDefaults.Divider(
                            color = Color.Gray,
                            thickness = 2.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Elemento:  ",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )

                            Text(
                                text = personaje.elemento,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                        }

                        TabRowDefaults.Divider(
                            color = Color.Gray,
                            thickness = 2.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Especie:  ",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )

                            Text(
                                text = personaje.especie,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                        }


                        TabRowDefaults.Divider(
                            color = Color.Gray,
                            thickness = 2.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Faccion:  ",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )

                            Text(
                                text = personaje.faccion,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Black
                            )
                        }


                        TabRowDefaults.Divider(
                            color = Color.Gray,
                            thickness = 2.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
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
                                painter = rememberImagePainter(personaje.imageUrl),
                                contentDescription = "Imagen de ${personaje.nombre}",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray),
                                contentScale = ContentScale.Crop,
                                alignment = Alignment.Center
                            )
                        }


                    }
                }

                Spacer(modifier = Modifier.height(16.dp))


                CustomSlider(personaje.ascension)


                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp) // Rellenar con espacio
                        .background(color = Color.Gray, shape = RoundedCornerShape(16.dp)) // Fondo gris y esquinas redondeadas
                        .padding(10.dp).clickable {
                            expandedStateEidolon.value = !expandedStateEidolon.value
                        } // Añadir un poco de relleno al contenido dentro del Box
                ) {
                    Text(
                        text = "Eidolones",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center).padding(start = 10.dp, end = 10.dp) // Alinear al centro
                            .clickable {
                                expandedStateEidolon.value = !expandedStateEidolon.value
                            } // Hacer clic sobre el texto para mostrar u ocultar los elementos
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))





                if (expandedStateEidolon.value) {
                    personaje.eidolon?.forEach { texto ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(8.dp) // Redondear cada tarjeta
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                            ) {

                                Text(
                                    text = "Eidolon:  ",
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black
                                )

                                Text(
                                    text = texto,
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp) // Rellenar con espacio
                        .background(color = Color.Gray, shape = RoundedCornerShape(16.dp)) // Fondo gris y esquinas redondeadas
                        .padding(10.dp).clickable {
                            expandedStateHabilidad.value = !expandedStateHabilidad.value
                        }// Añadir un poco de relleno al contenido dentro del Box
                ) {
                    Text(
                        text = "Habilidades",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.Center) // Alinear al centro
                            .clickable {
                                expandedStateHabilidad.value = !expandedStateHabilidad.value
                            } // Hacer clic sobre el texto para mostrar u ocultar los elementos
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (expandedStateHabilidad.value) {
                    Habilidades(personaje.habilidades)
                }

            }

        }


    }
}


@Composable
fun Habilidades(habilidad: Habilidades?) {

    imprimirHabilidad(habilidad?.atq_basico, "Atq_basico")
    imprimirHabilidad(habilidad?.habilidad_basica, "Habilidad_basica")
    imprimirHabilidad(habilidad?.habilidad_definitiva, "Habilidad_definitiva")
    imprimirHabilidad(habilidad?.talento, "Talento",habilidad?.mejoras)
    imprimirHabilidad(habilidad?.tecnica, "Tecnica")

}


@Composable
fun imprimirHabilidad(
    habilidad: Habilidad? = null,
    name: String,
    mejora: List<Mejora>? = null
) {

    val habilidadFinal = habilidad


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp) // Redondear cada tarjeta
    ) {

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                habilidadFinal?.nombre?.let {
                    Text(
                        text = "${name}: ${it}",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
            }

            habilidadFinal?.descripcion?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                habilidadFinal?.breakValue?.let {
                    Text(
                        text = "Break : " + it.toString(),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }

                habilidadFinal?.coste?.let {
                    Text(
                        text = "Coste : " + it.toString(),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }

                habilidadFinal?.gen_energia?.let {
                    Text(
                        text = "Gen_energia : " + it.toString(),
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
            }

            mejora?.forEach { mejora ->
                Text(
                    text = "Mejoras: ${mejora.nombre} - ${mejora.descripcion}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }


        if (habilidadFinal?.atq_individual != "no aplica") {
            habilidadFinal?.atq_individual?.let {
                Text(
                    text = "Daño:  " + it,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        } else {
            habilidadFinal?.atq_aoe?.let {
                Text(
                    text = "Daño:  " + it,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }


    }


}


