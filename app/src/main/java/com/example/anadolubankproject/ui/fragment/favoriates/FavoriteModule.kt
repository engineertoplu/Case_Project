package com.example.anadolubankproject.ui.fragment.favoriates

import com.example.anadolubankproject.di.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {
    @FragmentScope
    @Provides
    fun provideFavoriteListAdapter() = FavoriteListAdapter()
}
