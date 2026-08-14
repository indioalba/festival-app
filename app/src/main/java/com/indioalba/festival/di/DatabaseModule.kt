package com.indioalba.festival.di

import android.content.Context
import androidx.room.Room
import com.indioalba.festival.data.local.EventDao
import com.indioalba.festival.data.local.FestivalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideFestivalDatabase(
        @ApplicationContext context: Context
    ): FestivalDatabase {
        return Room.databaseBuilder(
            context,
            FestivalDatabase::class.java,
            "festival-database"
        ).build()
    }

    @Provides
    fun provideEventDao(database: FestivalDatabase): EventDao {
        return database.eventDao()
    }
}
