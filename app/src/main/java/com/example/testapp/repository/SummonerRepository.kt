package com.example.testapp.repository

import com.example.testapp.domain.service.CustomNetworkService
import javax.inject.Inject

class SummonerRepository @Inject constructor(
    private val networkService: CustomNetworkService
) {

    private val apiKey = "RGAPI-92f71f1c-7e33-4187-9192-5cceb3963078"

    fun getSummoner(summonerName: String) = networkService.getSummonerByName(summonerName, apiKey)

    fun getEntries(summonerId: String) = networkService.getLeagueEntries(summonerId, apiKey)

}