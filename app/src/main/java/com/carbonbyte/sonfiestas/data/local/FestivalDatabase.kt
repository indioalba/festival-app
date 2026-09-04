package com.carbonbyte.sonfiestas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carbonbyte.sonfiestas.data.model.Event

@Database(entities = [Event::class], version = 3)
abstract class FestivalDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
