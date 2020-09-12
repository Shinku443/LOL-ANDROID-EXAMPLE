package com.example.testapp.domain.service.response

data class SummonerResponse(
    val id: String,
    val accountId: String,
    val name: String,
    val summonerLevel: Long,
)