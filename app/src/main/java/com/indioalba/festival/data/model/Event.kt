package com.indioalba.festival.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val date: String,
    val time: String,
    val category: String,
    val location: String? = null,
    val imageUrl: String? = null,
    val isFavorite: Boolean = false,
)
