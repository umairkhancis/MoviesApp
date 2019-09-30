package com.noorifytech.moviesapp.data.dao.db.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"], tableName = "Movies")
data class MovieEntity(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: Long,

    @field:SerializedName("vote_average")
    val voteAverage: Float
)