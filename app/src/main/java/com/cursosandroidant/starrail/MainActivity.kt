package com.cursosandroidant.starrail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.cursosandroidant.starrail.di.dependencies.appModule
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
}

