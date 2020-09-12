package com.example.testapp.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.domain.usecase.GetSummonerEntries
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel @ViewModelInject constructor(
    private val getSummonerEntries: GetSummonerEntries
) : ViewModel() {

    data class State(
        val name: String? = null,
        val rank: String? = null,
        val level: String? = null,
        val wins: String? = null,
        val losses: String? = null,
        val percent: String? = null,
    )

    private val _liveData = MutableLiveData<State>()
    val liveData: LiveData<State> = _liveData

    private val disposables = CompositeDisposable()

    fun loadSummoner(summonerName: String) {
        val disposable = getSummonerEntries.execute(summonerName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { summoner, error ->
                if (error != null) {

                    Log.e("MainViewModel", error.toString())

                    return@subscribe
                }

                _liveData.postValue(
                    State(
                        name = summoner.summonerName,
                        rank = "${summoner.tier} ${summoner.rank}",
                        level = summoner.level.toString(),
                        wins = summoner.wins.toString(),
                        losses = summoner.losses.toString(),
                        percent = getPercent(summoner.wins, summoner.losses).toString()
                    )
                )
            }

        disposables.add(disposable)
    }

    private fun getPercent(wins: Int?, losses: Int?): Int? {
        return if (wins == null || losses == null) {
            null
        } else {
            ((wins.toDouble() / (wins + losses).toDouble()) * 100).toInt()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}