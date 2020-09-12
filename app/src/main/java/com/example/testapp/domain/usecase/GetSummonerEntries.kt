package com.example.testapp.domain.usecase

import com.example.testapp.repository.SummonerRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSummonerEntries @Inject constructor(
    private val summonerRepository: SummonerRepository
) {

    fun execute(summonerName: String): Single<Summoner> {
        return summonerRepository.getSummoner(summonerName).map {
            Summoner(
                id = it.id,
                summonerName = it.name,
                level = it.summonerLevel
            )
        }.flatMap { summoner ->
            if (summoner.id == null) throw SummonerNotFoundException()
            summonerRepository.getEntries(summoner.id)
                .map {
                    if (it.isNotEmpty()) {
                        with(it.first()) {
                            Summoner(
                                id = summoner.id,
                                summonerName = summoner.summonerName,
                                level = summoner.level,
                                leagueId = leagueId,
                                queueType = queueType,
                                tier = tier,
                                rank = rank,
                                leaguePoints = leaguePoints,
                                wins = wins,
                                losses = losses,
                                hotStreak = hotStreak,
                                veteran = veteran,
                                freshBlood = freshBlood
                            )
                        }
                    } else summoner
                }
        }
    }

    class SummonerNotFoundException() : Exception()

}