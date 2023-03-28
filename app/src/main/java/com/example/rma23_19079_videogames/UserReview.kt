package com.example.rma23_19079_videogames

data class UserReview(
    override val username: String,
    override val timestamp: Long,
    val review: String
):UserImpression()

