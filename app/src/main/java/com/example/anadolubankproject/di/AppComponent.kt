package com.example.anadolubankproject.di

import com.example.anadolubankproject.AppControl
import com.example.anadolubankproject.di.module.AppModule
import com.example.anadolubankproject.di.module.ViewBuilderModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ViewBuilderModule::class])
interface AppComponent : AndroidInjector<AppControl> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AppControl>()
}


