package com.example.moviesapp.common.dependancyinjection

import com.example.moviesapp.movies.FetchMovieDetailsUseCase
import com.example.moviesapp.movies.FetchMoviesUseCase
import com.example.moviesapp.movies.MoviesRemoteDataSource
import com.example.moviesapp.screens.ScreensNavigator
import com.example.moviesapp.screens.common.views.ViewMvcFactory
import com.example.moviesapp.screens.dialogs.DialogsNavigator
import java.lang.reflect.Field

class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return clientClass.declaredFields
    }


    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotations = field.annotations
        for (annotation in fieldAnnotations) {
            if (annotation is Service) {
                return true
            }
        }
        return false
    }

    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClient(field.type))
        field.isAccessible = isAccessibleInitially
    }

    private fun getServiceForClient(type: Class<*>?): Any {
        when (type) {
            DialogsNavigator::class.java -> {
                return compositionRoot.dialogsNavigator
            }
            ScreensNavigator::class.java -> {
                return compositionRoot.screenNavigator
            }
            FetchMoviesUseCase::class.java -> {
                return compositionRoot.fetchMoviesUseCase
            }

            FetchMovieDetailsUseCase::class.java -> {
                return compositionRoot.fetchMovieDetailsUseCase
            }

            MoviesRemoteDataSource::class.java -> {
                return compositionRoot.moviesRemoteDataSource
            }

            ViewMvcFactory::class.java -> {
                return compositionRoot.viewMvcFactory
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }
}