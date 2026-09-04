package com.carbonbyte.sonfiestas.data.remote

import com.carbonbyte.sonfiestas.data.model.Event
import retrofit2.http.GET
import retrofit2.http.Query

interface FestivalApi {
    @GET("agenda")
    suspend fun getAgenda(
        @Query("id") festivalId: String,
    ): List<Event>
}
