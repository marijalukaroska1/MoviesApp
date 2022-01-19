package com.example.moviesapp.common.dependancyinjection.activity

import com.example.moviesapp.common.dependancyinjection.presentation.PresentationComponent
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule
import com.example.moviesapp.common.dependancyinjection.presentation.UseCasesModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    //constructs the presentation component
    fun newPresentationComponent(presentationModule: PresentationModule, useCasesModule: UseCasesModule): PresentationComponent
}