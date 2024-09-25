package com.aneeque.starzplayandroidsimulatedvideoapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aneeque.domain.retrofit.models.MediaDetails
import com.aneeque.starzplayandroidsimulatedvideoapp.R
import com.aneeque.starzplayandroidsimulatedvideoapp.databinding.ActivityMediaDetailsBinding
import com.aneeque.starzplayandroidsimulatedvideoapp.helpers.AppConstants
import com.bumptech.glide.Glide

class MediaDetailsActivity : AppCompatActivity() {
    private var _binding: ActivityMediaDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mediaDetails: MediaDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initViewBinding()
        extractIntentData()
        initViews()
    }

    private fun initViews() {
        Glide.with(this)
            .load(AppConstants.MEDIA_BASE_URL + mediaDetails.backdropPath).into(binding.ivMedia)

        binding.btnPlayMedia.setOnClickListener {
            startActivity(Intent(this, MediaPlayerActivity::class.java))
        }


        binding.tvMediaTitle.text = mediaDetails.title ?: mediaDetails.name
        binding.tvMediaDescription.text = mediaDetails.overview
    }

    private fun extractIntentData() {
        mediaDetails =
            intent.getSerializableExtra(AppConstants.MEDIA_DETAIL_OBJ_KEY) as MediaDetails
    }

    private fun initViewBinding() {
        _binding = ActivityMediaDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}