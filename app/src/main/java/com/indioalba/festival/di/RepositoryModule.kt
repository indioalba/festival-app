package com.indioalba.festival.di

import com.indioalba.festival.data.repository.FestivalRepository
import com.indioalba.festival.data.repository.OfflineFirstFestivalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFestivalRepository(
        repository: OfflineFirstFestivalRepository,
    ): FestivalRepository
}
