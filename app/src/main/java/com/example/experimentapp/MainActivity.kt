package com.example.experimentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.experimentapp.databinding.ActivityMainBinding
import com.example.experimentapp.paging3.ui.PagingActivity
import com.example.experimentapp.animatedsearch.SearchActivity
import com.example.experimentapp.stickysection.StickySectionActivity
import com.example.experimentapp.videoplayer.VideoPlayerActivity
import com.example.experimentapp.workmanager.WorkerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGotoStickySection .setOnClickListener {
            startActivity(Intent(this, StickySectionActivity::class.java))
        }

        binding.buttonGotoSearchScreen.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        binding.buttonGotoPaginationScreen.setOnClickListener {
            startActivity(Intent(this, PagingActivity::class.java))
        }

        binding.buttonGotoWorkManagerScreen.setOnClickListener {
            startActivity(Intent(this, WorkerActivity::class.java))
        }

        binding.buttonVideoPlayerScreen.setOnClickListener {
            startActivity(Intent(this, VideoPlayerActivity::class.java))
        }
    }
}
