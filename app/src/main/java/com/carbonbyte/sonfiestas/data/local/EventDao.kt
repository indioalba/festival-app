package com.carbonbyte.sonfiestas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carbonbyte.sonfiestas.data.model.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(events: List<Event>)

    @Query("SELECT * FROM events LIMIT 1")
    fun getAnyEvent(): Event?

    @Query("SELECT * FROM events WHERE id = :id")
    fun getEvent(id: Int): Flow<Event?>

    @Query("UPDATE events SET isFavorite = NOT isFavorite WHERE id = :id")
    fun toggleFavorite(id: Int)

    @Query("DELETE FROM events")
    fun deleteAllEvents()
}
