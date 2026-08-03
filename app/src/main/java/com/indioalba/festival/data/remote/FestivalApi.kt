package com.indioalba.festival.data.remote

import com.indioalba.festival.data.model.Event
import retrofit2.http.GET
import retrofit2.http.Query

interface festivalApi {
    @GET("agenda")
    suspend fun getAgenda(
        @Query("id") festivalId: String
    ): List<Event>
}
