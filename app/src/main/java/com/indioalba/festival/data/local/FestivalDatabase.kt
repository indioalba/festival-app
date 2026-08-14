package com.indioalba.festival.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indioalba.festival.data.model.Event

@Database(entities = [Event::class], version = 1)
abstract class FestivalDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
