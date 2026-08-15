package com.indioalba.festival.data.repository

import com.indioalba.festival.data.model.Event
import kotlinx.coroutines.flow.Flow

interface FestivalRepository {
    fun getAgenda(): Flow<List<Event>>
    fun getEvent(id: Int): Flow<Event?>
    suspend fun toggleFavorite(id: Int)
    suspend fun refreshAgenda(festivalId: String)
}
