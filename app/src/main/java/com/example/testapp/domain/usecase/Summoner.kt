package com.example.testapp.domain.usecase

data class Summoner(
    val id: String? = null,
    val summonerName: String? = null,
    val level: Long? = null,
    val leagueId: String? = null,
    val queueType: String? = null,
    val tier: String? = null,
    val rank: String? = null,
    val leaguePoints: Int? = null,
    val wins: Int? = null,
    val losses: Int? = null,
    val hotStreak: Boolean? = null,
    val veteran: Boolean? = null,
    val freshBlood: Boolean? = null
)