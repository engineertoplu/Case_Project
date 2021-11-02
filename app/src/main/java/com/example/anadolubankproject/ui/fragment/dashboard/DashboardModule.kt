package com.example.anadolubankproject.ui.fragment.dashboard

import com.example.anadolubankproject.di.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class DashboardModule {
    @FragmentScope
    @Provides
    fun provideDasboardListAdapter() = DasboardListAdapter()

}
