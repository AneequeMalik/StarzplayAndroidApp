package com.aneeque.starzplayandroidsimulatedvideoapp.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.aneeque.starzplayandroidsimulatedvideoapp.R
import com.aneeque.starzplayandroidsimulatedvideoapp.databinding.ActivityMediaPlayerBinding

class MediaPlayerActivity : AppCompatActivity() {
    private var _binding: ActivityMediaPlayerBinding? = null
    private val binding get() = _binding!!
    private lateinit var exoPlayer: ExoPlayer
    private var playbackPosition = 0L
    private val url =
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
    }

    private fun initViewBinding() {
        enableEdgeToEdge()
        _binding = ActivityMediaPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    private fun initializePlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        preparePlayer()
    }

    private fun preparePlayer() {
        exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(url)))
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        binding.player.player = exoPlayer
    }


    private fun releasePlayer() {
        playbackPosition = exoPlayer.currentPosition
        exoPlayer.release()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        releasePlayer()
    }
}