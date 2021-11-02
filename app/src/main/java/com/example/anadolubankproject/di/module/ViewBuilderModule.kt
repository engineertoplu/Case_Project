package com.example.anadolubankproject.di.module

import com.example.anadolubankproject.di.FragmentScope
import com.example.anadolubankproject.ui.activity.MainActivity
import com.example.anadolubankproject.ui.fragment.dashboard.DashboardModule
import com.example.anadolubankproject.ui.fragment.dashboard.DashboardFragment
import com.example.anadolubankproject.ui.fragment.detail.DetailFragment
import com.example.anadolubankproject.ui.fragment.favoriates.FavoriteModule
import com.example.anadolubankproject.ui.fragment.favoriates.FavoritesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewBuilderModule {
    //Activities

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    //Fragments

    @FragmentScope
    @ContributesAndroidInjector(modules = [(DashboardModule::class)])
    internal abstract fun bindDashboardFragment(): DashboardFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [(FavoriteModule::class)])
    internal abstract fun bindFavoritesFragment(): FavoritesFragment

    @FragmentScope
    @ContributesAndroidInjector()
    internal abstract fun bindDetailFragment(): DetailFragment
}