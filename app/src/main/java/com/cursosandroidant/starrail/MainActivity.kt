package com.cursosandroidant.starrail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import cafe.adriel.voyager.navigator.Navigator
import com.cursosandroidant.starrail.di.dependencies.appModule
import com.cursosandroidant.starrail.ui.presentation.materiales.OneMaterialScreen
import com.cursosandroidant.starrail.ui.presentation.personaje.PersonajeScreen
import org.koin.core.context.GlobalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigator(screen = PersonajeScreen())
        }
        GlobalContext.startKoin {
            modules(appModule) // Aquí registramos nuestro módulo
        }
    }

//    @Composable
//    fun NavGraph(navController: NavHostController) {
//        NavHost(navController = navController, startDestination = "home") {
//            composable("home") {
//                HomeScreen(navController = navController)
//            }
//            composable(
//                "oneMaterial/{materialId}",
//                arguments = listOf(navArgument("materialId") { type = NavType.StringType })
//            ) { backStackEntry ->
//                val materialId = backStackEntry.arguments?.getString("materialId")
//                OneMaterialScreen(materialId = materialId)
//            }
//        }
//    }
//    @Composable
//    fun HomeScreen(navController: NavController) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Button(onClick = {
//                navController.navigate("oneMaterial/123")  // Navegar a la pantalla con el ID de material
//            }) {
//                Text("Ir a Material")
//            }
//        }
//    }

}

