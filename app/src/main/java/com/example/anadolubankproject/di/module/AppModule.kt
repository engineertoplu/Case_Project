package com.example.anadolubankproject.di.module

import android.content.Context
import com.example.anadolubankproject.AppControl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NetworkModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: AppControl): Context = application.applicationContext
}