package com.carbonbyte.sonfiestas.data.remote

import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class FestivalApiTest {
    private lateinit var server: MockWebServer
    private lateinit var api: FestivalApi

    @Before
    fun setup() {
        server = MockWebServer()
        val json = Json { ignoreUnknownKeys = true }
        api =
            Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(FestivalApi::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `getAgenda should return list of events`() =
        runTest {
            val responseBody =
                """
                [
                    {
                        "id": 1,
                        "title": "Summer Concert",
                        "date": "2026-08-15",
                        "time": "20:00",
                        "category": "Music",
                        "location": "Main Stage",
                        "imageUrl": "https://example.com/image.jpg",
                        "isFavorite": false
                    }
                ]
                """.trimIndent()

            server.enqueue(MockResponse().setBody(responseBody).setResponseCode(200))

            val events = api.getAgenda("festival-123")

            assertEquals(1, events.size)
            assertEquals("Summer Concert", events[0].title)

            val request = server.takeRequest()
            assertEquals("/agenda?id=festival-123", request.path)
        }
}
