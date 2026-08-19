package com.carbonbyte.sonfiestas.di

import com.carbonbyte.sonfiestas.data.repository.FestivalRepository
import com.carbonbyte.sonfiestas.data.repository.OfflineFirstFestivalRepository
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
