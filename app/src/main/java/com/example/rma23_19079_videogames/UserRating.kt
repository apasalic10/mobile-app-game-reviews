package com.example.rma23_19079_videogames

data class UserRating(
    override val username: String,
    override val timestamp: Long,
    val rating: Double
): UserImpression()

