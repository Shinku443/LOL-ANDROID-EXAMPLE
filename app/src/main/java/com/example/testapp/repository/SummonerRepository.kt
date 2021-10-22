package com.example.testapp.repository

import com.example.testapp.domain.service.CustomNetworkService
import javax.inject.Inject

class SummonerRepository @Inject constructor(
    private val networkService: CustomNetworkService
) {

    private val apiKey = "RGAPI-d9a235bc-d629-431b-9d71-2f3bf12a1da1"

    fun getSummoner(summonerName: String) = networkService.getSummonerByName(summonerName, apiKey)

    fun getEntries(summonerId: String) = networkService.getLeagueEntries(summonerId, apiKey)

}