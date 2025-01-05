package com.cursosandroidant.starrail.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.cursosandroidant.starrail.R



interface ConNombre {
    val nombre: String
}

interface ConPersonaje : ConNombre {
    val elemento: String
    val via: String
}

interface ConArtefacto : ConNombre {
    // Otros atributos específicos
}

interface ConMaterial : ConNombre {
    // Otros atributos específicos
}

interface ConCono : ConNombre {
    val via: String
}

@Composable
fun <T : ConNombre> FiltroBar(name: String, lista: List<T>): List<T> {
    var searchQuery by remember { mutableStateOf("") }
    var searchElement by remember { mutableStateOf("") }
    var searchVia by remember { mutableStateOf("") }
    var showPopup by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var Filtrado by remember { mutableStateOf<List<T>>(emptyList()) }
    var triggerUpdate by remember { mutableStateOf(0) }

    LaunchedEffect(lista) {
        Filtrado = lista // Carga la lista completa inicialmente
    }

    fun aplicarFiltro() {

        Filtrado = lista.filter { item ->
            // Filtra primero por nombre, luego por los demás filtros
            item.nombre.contains(searchQuery, ignoreCase = true) &&
                    if (item is ConPersonaje) {
                        // Verifica si se debe filtrar por elemento, vía o ambos
                        val isElementMatch = if (searchElement.isNotEmpty()) {
                            item.elemento.contains(searchElement, ignoreCase = true)
                        } else {
                            true // Si no hay filtro de elemento, siempre es verdadero
                        }

                        val isViaMatch = if (searchVia.isNotEmpty()) {
                            item.via.contains(searchVia, ignoreCase = true)
                        } else {
                            true // Si no hay filtro de vía, siempre es verdadero
                        }

                        // El filtro se aplica solo si al menos uno de los filtros (elemento o vía) es verdadero
                        isElementMatch && isViaMatch
                    } else if (item is ConCono) {
                        // Filtro por vía solo si searchVia tiene un valor
                        searchVia.isEmpty() || item.via.contains(searchVia, ignoreCase = true)
                    } else {
                        true // Si no es ni ConPersonaje ni ConCono, no se aplica filtro
                    }
        }
    }



    LaunchedEffect(searchQuery, triggerUpdate) {
        Log.d("Debug", "triggerUpdate: $triggerUpdate")
        Log.d("Debug", "searchQuery: $searchQuery")
        Filtrado = if (searchQuery.isEmpty()) {
            lista // Muestra toda la lista si no hay filtro de nombre
        } else {
            lista.filter { item ->
                item.nombre.contains(searchQuery, ignoreCase = true)
            }
        }


        //aplicarFiltro()
    }

    // Barra de búsqueda
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Filtrar $name", color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp),
            trailingIcon = {
                IconButton(onClick = { showPopup = !showPopup }) {
                    Icon(
                        painter = painterResource(id = com.cursosandroidant.starrail.R.drawable.filter),
                        contentDescription = "Buscar",
                        tint = Color.White
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Color.White
            )
        )

        // Mostrar Popup para elementos y vías
        if (showPopup && name == "personajes") {
            Popup(
                alignment = Alignment.BottomStart,
                onDismissRequest = { showPopup = false }
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(Color.White)
                        .padding(start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Text(
                        text = "Filtro por elemento: ",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val images = listOf(
                            R.drawable.imaginario to "imaginario",
                            R.drawable.quamtum to "quamtum",
                            R.drawable.thumder to "thumder",
                            R.drawable.fisico to "fisico",
                            R.drawable.ice to "ice",
                            R.drawable.wind to "anemo",
                            R.drawable.flame to "flame"
                        )

                        images.forEach { (imageRes, description) ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .clickable {
                                        searchElement = description
                                        // Aplica el filtro solo al hacer clic
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = description,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }

                    Text(
                        text = "Filtro por via: ",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val images = listOf(
                            R.drawable.preservation to "preservacion",
                            R.drawable.hunt to "caceria",
                            R.drawable.destrucion to "destruccion",
                            R.drawable.abundancia to "abundancia",
                            R.drawable.armonia to "harmonia",
                            R.drawable.erudicion to "erudicion",
                            R.drawable.nihiliadad to "nihilidad"
                        )

                        images.forEach { (imageRes, description) ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .clickable {
                                        searchVia = description

                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = description,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }

                    Row(modifier = Modifier.padding(2.dp)) {
                        Button(onClick = {
                            aplicarFiltro()
                            showPopup = false// Aplica el filtro cuando se hace clic en el botón
                        }) {
                            Text("Filtrar")
                        }

                        Button(onClick = {
                            Filtrado = lista
                            showPopup = false
                        }) {
                            Text("Clean")
                        }
                    }

                }
            }
        }


        if (showPopup && name == "conos") {
            Popup(
                alignment = Alignment.BottomStart,
                onDismissRequest = { showPopup = false }
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(Color.White)
                        .padding(start = 16.dp, end = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {

                    Text(
                        text = "Filtro por via: ",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val images = listOf(
                            R.drawable.preservation to "preservacion",
                            R.drawable.hunt to "caceria",
                            R.drawable.destrucion to "destruccion",
                            R.drawable.abundancia to "abundancia",
                            R.drawable.armonia to "harmonia",
                            R.drawable.erudicion to "erudicion",
                            R.drawable.nihiliadad to "nihilidad"
                        )

                        images.forEach { (imageRes, description) ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f)
                                    .clickable {
                                        searchVia = description
                                        // Aplica el filtro solo al hacer clic
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = description,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }

                    Button(onClick = {
                        aplicarFiltro()
                        showPopup = false
                    }) {
                        Text("Filtro")
                    }
                }
            }
        }
    }

    return Filtrado
}
