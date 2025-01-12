package com.cursosandroidant.starrail.di.dependencies

import com.cursosandroidant.starrail.data.apiClient.GetAllConosImgImp
import com.cursosandroidant.starrail.data.apiClient.GetAllConosImp
import com.cursosandroidant.starrail.data.apiClient.GetAllMaterialesImgImp
import com.cursosandroidant.starrail.data.apiClient.GetAllMaterialesImp
import com.cursosandroidant.starrail.data.apiClient.GetAllPersonajesImgImpl
import com.cursosandroidant.starrail.data.apiClient.GetAllPersonajesImpl
import com.cursosandroidant.starrail.data.apiClient.GetCharactersUsingMaterialRepImp
import com.cursosandroidant.starrail.data.apiClient.GetOneMaterialesImp
import com.cursosandroidant.starrail.data.apiClient.getAllArtefactoImgImp
import com.cursosandroidant.starrail.data.apiClient.getAllArtefactoImp
import com.cursosandroidant.starrail.domain.repositories.GetAllArtefactoImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllArtefactoRep
import com.cursosandroidant.starrail.domain.repositories.GetAllConosImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllConosRep
import com.cursosandroidant.starrail.domain.repositories.GetAllMaterialesImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllMaterialesRep
import com.cursosandroidant.starrail.domain.repositories.GetAllPersonajesImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllPersonajesRep
import com.cursosandroidant.starrail.domain.repositories.GetCharactersUsingMaterialRep
import com.cursosandroidant.starrail.domain.repositories.GetOneMaterialesRep
import com.cursosandroidant.starrail.domain.usecase.GetAllArtefactoImgUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllArtefactoUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllConosImgUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllConosUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllMaterialesImgUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllMaterialesUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllPersonajesImgUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllPersonajesUseCase
import com.cursosandroidant.starrail.domain.usecase.GetCharactersUsingMaterialUseCase
import com.cursosandroidant.starrail.domain.usecase.GetOneMaterialesUseCase
import com.cursosandroidant.starrail.ui.presentation.artefactos.ArtefactosViewModel
import com.cursosandroidant.starrail.ui.presentation.conos.ConosViewModel
import com.cursosandroidant.starrail.ui.presentation.materiales.MaterialesViewModel
import com.cursosandroidant.starrail.ui.presentation.personaje.PersonajeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<GetAllPersonajesRep> { (GetAllPersonajesImpl()) }
    single <GetAllPersonajesImgRep>{ (GetAllPersonajesImgImpl()) }
    single<GetAllArtefactoRep> { (getAllArtefactoImp()) }
    single <GetAllArtefactoImgRep>{ (getAllArtefactoImgImp()) }
    single<GetAllConosRep> { (GetAllConosImp()) }
    single <GetAllConosImgRep>{ (GetAllConosImgImp()) }
    single<GetAllMaterialesRep> { (GetAllMaterialesImp()) }
    single <GetAllMaterialesImgRep>{ (GetAllMaterialesImgImp()) }
    single <GetCharactersUsingMaterialRep>{ (GetCharactersUsingMaterialRepImp()) }
    single <GetOneMaterialesRep>{ (GetOneMaterialesImp()) }

    single { GetAllPersonajesUseCase(get()) }
    single { GetAllPersonajesImgUseCase(get()) }
    single { GetAllArtefactoUseCase(get()) }
    single { GetAllArtefactoImgUseCase(get()) }
    single { GetAllConosUseCase(get()) }
    single { GetAllConosImgUseCase(get()) }
    single { GetAllMaterialesUseCase(get()) }
    single { GetAllMaterialesImgUseCase(get()) }
    single { GetCharactersUsingMaterialUseCase(get()) }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { GetOneMaterialesUseCase(get()) }


    viewModel { PersonajeViewModel(get(), get(),get(),get()) }
    viewModel { ArtefactosViewModel(get(), get()) }
    viewModel { ConosViewModel(get(), get()) }
    viewModel { MaterialesViewModel(get(), get(),get(),get(),get()) }

}