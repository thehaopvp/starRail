package com.cursosandroidant.starrail.ui.presentation.conos

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.rememberImagePainter
import com.cursosandroidant.starrail.data.model.Cono
import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.ui.components.BottomNavigationBar
import com.cursosandroidant.starrail.ui.components.FiltroBar
import com.cursosandroidant.starrail.ui.presentation.personaje.OneCharacterScreen
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

class ConosScreen : Screen {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun Content() {

        Scaffold(
            topBar = { /* Add your top bar here */ },
            content = { paddingValues ->
                Main(modifier = Modifier.padding(paddingValues))
            },
            floatingActionButton = { /* Add your FAB here */ },
            bottomBar = { BottomNavigationBar(LocalNavigator.currentOrThrow) {} },
            containerColor = Color.Black // Set the background color of Scaffold
        )

    }

    @SuppressLint("SuspiciousIndentation")
    @Composable
    fun Main(modifier: Modifier = Modifier) {

        var ConosViewModel : ConosViewModel= koinViewModel()
        val lifecycleOwner = LocalLifecycleOwner.current

        val conosLista by ConosViewModel.conosLista.collectAsState()
        val isLoading by ConosViewModel.isLoading.observeAsState(false)
        val context = LocalContext.current


        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 60.dp, bottom = 8.dp),
                text = "Conos"
            )

            var conosFiltrados = FiltroBar("conos", conosLista)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(conosFiltrados.chunked(3)) { personaje ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth() // Esto asegura que el Row ocupe todo el ancho disponible
                            .padding(horizontal = 6.dp) // Espacio entre las tarjetas
                    ) {
                        personaje.forEachIndexed { index, item ->
                            // Pasamos un modifier con el ancho calculado
                            ConoCard(cono = item, modifier = Modifier.weight(1f))
                        }

                        // Si hay menos de 3 elementos en la fila, agregamos espacio vacío
                        if (personaje.size < 3) {
                            val emptySpaceWeight = 1f * (3 - personaje.size) // Determina el peso de los espacios vacíos
                            Spacer(modifier = Modifier.weight(emptySpaceWeight)) // Añadir espacio vacío
                        }
                    }
                }
            }
        }

    }


    @Composable
    fun ConoCard(cono: Cono, modifier: Modifier = Modifier) {
        val navigator = LocalNavigator.currentOrThrow
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp // en dp
        val density = LocalDensity.current.density
        val screenWidthPx = screenWidth * density // en píxeles
        val thirdOfScreenPx = screenWidthPx / 3

        // Aquí nos aseguramos de que el Column ocupe un tercio del ancho
        Column(
            modifier = modifier
                .clickable {
                    navigator.push(OneConoScreen(Gson().toJson(cono)))
                }
                .padding(8.dp)
                .width(thirdOfScreenPx.dp) // Esto limita el ancho de cada tarjeta
                .heightIn(max = 150.dp, min = 150.dp) // Limita la altura para que las tarjetas no se estiren
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(cono.imageUrl),
                contentDescription = "Imagen de ${cono.nombre}",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = cono.nombre,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}