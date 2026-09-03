package com.carbonbyte.sonfiestas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carbonbyte.sonfiestas.data.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
abstract class EventDao {
    @Query("SELECT * FROM events")
    abstract fun getAllEvents(): Flow<List<Event>>

    @Query("SELECT * FROM events")
    abstract fun getAllEventsSync(): List<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(events: List<Event>)

    @Query("SELECT * FROM events LIMIT 1")
    abstract fun getAnyEvent(): Event?

    @Query("SELECT * FROM events WHERE id = :id")
    abstract fun getEvent(id: Int): Flow<Event?>

    @Query("UPDATE events SET isFavorite = NOT isFavorite WHERE id = :id")
    abstract fun toggleFavorite(id: Int)

    @Query("DELETE FROM events")
    abstract fun deleteAllEvents()

    @androidx.room.Transaction
    open fun refreshEvents(events: List<Event>) {
        deleteAllEvents()
        insertAll(events)
    }
}
