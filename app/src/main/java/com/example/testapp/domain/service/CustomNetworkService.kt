package com.example.testapp.domain.service

import com.example.testapp.domain.service.response.EntriesResponse
import com.example.testapp.domain.service.response.SummonerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomNetworkService {

    @GET("lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummonerByName(
        @Path("summonerName") summonerName: String,
        @Query("api_key") apiKey: String
    ): Single<SummonerResponse>

    @GET("lol/league/v4/entries/by-summoner/{summonerId}")
    fun getLeagueEntries(
        @Path("summonerId") summonerId: String,
        @Query("api_key") apiKey: String
    ): Single<List<EntriesResponse>>

}