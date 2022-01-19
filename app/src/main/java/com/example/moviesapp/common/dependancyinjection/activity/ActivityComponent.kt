package com.example.moviesapp.common.dependancyinjection.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationComponent
import com.example.moviesapp.common.dependancyinjection.presentation.PresentationModule
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    //constructs the presentation component
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent


    @Subcomponent.Builder
    interface Builder {
        //puts the activity instance on the object graph so other services can use it
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivityComponent
    }
}