package com.indioalba.festival.data.repository

import com.indioalba.festival.data.model.Event
import kotlinx.coroutines.flow.Flow

interface FestivalRepository {
    fun getAgenda(): Flow<List<Event>>
    suspend fun refreshAgenda(festivalId: String)
}
