package com.example.experimentapp.audioplayer

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.experimentapp.R
import com.example.experimentapp.databinding.ActivityAudioPlayerBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AudioPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAudioPlayerBinding
    private var player: ExoPlayer? = null
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player = ExoPlayer.Builder(this).build()

        val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
        val mediaItem = MediaItem.fromUri(audioUrl)
        player?.setMediaItem(mediaItem)
        player?.prepare()

        binding.btnPlayPause.setOnClickListener {
            if (isPlaying) {
                player?.pause()
                binding.btnPlayPause.text = getString(R.string.play_audio)
            } else {
                player?.play()
                binding.btnPlayPause.text = getString(R.string.pause_audio)
                startSeekBarUpdate()
            }
            isPlaying = !isPlaying
        }

        binding.audioSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player?.seekTo(progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun startSeekBarUpdate() {
        lifecycleScope.launch {
            while (player?.isPlaying == true) {
                player?.let {
                    binding.audioSeekBar.max = it.duration.toInt()
                    binding.audioSeekBar.progress = it.currentPosition.toInt()
                }
                delay(1000L)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }
}