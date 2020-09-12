package com.example.testapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        searchButton.setOnClickListener {
            viewModel.loadSummoner(summonerNameEditText.text.toString())
        }

        viewModel.liveData.observe(this, { state ->
            nameTextView.text = state.name
            rankTextView.text = state.rank
            levelTextView.text = getString(R.string.level, state.level)
            winTextView.text = state.wins
            lossTextView.text = state.losses
            percentTextView.text = getString(R.string.win_ratio, state.percent)
        })
    }
}