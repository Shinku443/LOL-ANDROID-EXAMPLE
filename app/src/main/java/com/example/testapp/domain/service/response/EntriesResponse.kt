package com.example.testapp.domain.service.response

data class EntriesResponse(
    val summonerName: String,
    val leagueId: String,
    val queueType: String,
    val tier: String,
    val rank: String,
    val leaguePoints: Int,
    val wins: Int,
    val losses: Int,
    val hotStreak: Boolean,
    val veteran: Boolean,
    val freshBlood: Boolean
)