package com.cursosandroidant.starrail.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cursosandroidant.starrail.ui.presentation.artefactos.ArtefactosScreen
import com.cursosandroidant.starrail.ui.presentation.conos.ConosScreen
import com.cursosandroidant.starrail.ui.presentation.materiales.MaterialesScreen
import com.cursosandroidant.starrail.ui.presentation.personaje.PersonajeScreen


sealed class BottomNavigationStar(
    val route: String,
    val resourceId: Screen, val iconComposable: @Composable () -> Unit, val label: String
) {
    object character : BottomNavigationStar("character", PersonajeScreen(),
        {
            Icon(
                ImageVector.vectorResource(id = com.cursosandroidant.starrail.R.drawable.personajes),
                contentDescription = "Personajes",
                tint = Color.White  // Aplica tinte blanco al ícono
            )
        }, label = "Personajes"
    )


    object artefactos : BottomNavigationStar(
        "artefactos",
        ArtefactosScreen(),
        {
            Icon(
                ImageVector.vectorResource(id = com.cursosandroidant.starrail.R.drawable.diamond),
                contentDescription = "Artefactos",
                tint = Color.White  // Aplica tinte blanco al ícono
            )
        }, label = "Artefactos")

    object conos :
        BottomNavigationStar(
            "Conos",
            ConosScreen(),
            {
                Icon(
                    ImageVector.vectorResource(id = com.cursosandroidant.starrail.R.drawable.conos),
                    contentDescription = "Conos",
                    tint = Color.White  // Aplica tinte blanco al ícono
                )
            }, label = "Conos")

    object materiales : BottomNavigationStar(
        "Materiales",
        MaterialesScreen(),
        {
            Icon(
                ImageVector.vectorResource(id = com.cursosandroidant.starrail.R.drawable.lists),
                contentDescription = "Materiales",
                tint = Color.White  // Aplica tinte blanco al ícono
            )
        }, label = "Materiales")
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(navigator: Navigator, onClicked: () -> Unit) {

    val bottomNavigationItems = listOf(
        BottomNavigationStar.character,
        BottomNavigationStar.artefactos,
        BottomNavigationStar.conos,
        BottomNavigationStar.materiales,

        )

    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White,
    ) {
        val currentRoute = LocalNavigator.currentOrThrow.lastItem

        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.iconComposable() },
                label = { Text(text = screen.label, color = Color.White) },
                selected = screen.resourceId == currentRoute,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterVertically),
                onClick = {
                    if (currentRoute != screen.resourceId) {
                        navigator.push(screen.resourceId)
                    }
                }

            )
        }
    }


}

