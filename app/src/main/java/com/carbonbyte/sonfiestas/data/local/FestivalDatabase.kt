package com.carbonbyte.sonfiestas.data.local

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import com.carbonbyte.sonfiestas.data.model.Event

@Keep
@Database(entities = [Event::class], version = 6)
abstract class FestivalDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}
