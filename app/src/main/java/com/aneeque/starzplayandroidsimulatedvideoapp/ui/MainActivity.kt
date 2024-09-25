package com.aneeque.starzplayandroidsimulatedvideoapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aneeque.domain.retrofit.models.MediaDetails
import com.aneeque.starzplayandroidsimulatedvideoapp.adapters.RecentItemsAdapter
import com.aneeque.starzplayandroidsimulatedvideoapp.adapters.SearchMediaAdapter
import com.aneeque.starzplayandroidsimulatedvideoapp.base.StarzplayApp
import com.aneeque.starzplayandroidsimulatedvideoapp.databinding.ActivityMainBinding
import com.aneeque.starzplayandroidsimulatedvideoapp.helpers.AppConstants
import com.aneeque.starzplayandroidsimulatedvideoapp.helpers.Utils
import com.aneeque.starzplayandroidsimulatedvideoapp.interfaces.MediaItemListeners
import com.aneeque.starzplayandroidsimulatedvideoapp.types.MediaType
import com.aneeque.starzplayandroidsimulatedvideoapp.viewmodel.StarzplaySearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MediaItemListeners {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    // ViewModel with delegated viewModels
    private val starzplaySearchViewModel: StarzplaySearchViewModel by viewModels()

    // Variables to hold media data
    private var moviesList = ArrayList<MediaDetails>()
    private var tvShowList = ArrayList<MediaDetails>()
    private var otherList = ArrayList<MediaDetails>()
    private var mediaTypes = mutableListOf<MediaType>()

    // Debounce job
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initListeners()
        getListFromDB()
    }

    private fun getListFromDB() {
        lifecycleScope.launch(Dispatchers.IO) {
            val list = StarzplayApp.database?.mediaDao()?.getRecentSearchList()
            list?.let {
                if (it.isNotEmpty()) {
                    binding.rvRecentSearch.visibility = View.VISIBLE
                    binding.tvRecentSearchPrompt.visibility = View.GONE
                    binding.rvRecentSearch.apply {
                        layoutManager =
                            LinearLayoutManager(
                                this@MainActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                        adapter = RecentItemsAdapter(this@MainActivity, it)
                    }
                } else {
                    binding.rvRecentSearch.visibility = View.GONE
                    binding.tvRecentSearchPrompt.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initViews() {
        binding.searchView.setupWithSearchBar(binding.searchBar)
        setupRecyclerView()
    }

    private fun initListeners() {
        binding.searchView.editText.doOnTextChanged { text, _, _, _ ->
            // Cancel previous job if any, debounce search input
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(500) // Add 500ms debounce delay
                text?.let {
                    if (it.isNotEmpty()) {
                        performSearch(it.toString())
                    }
                }
            }
        }
    }

    private suspend fun performSearch(query: String) {
        starzplaySearchViewModel.observeMediaLiveData(query, "1")
            .observe(this@MainActivity) { response ->
                if (response?.isSuccessful == true) {
                    response.body()?.results?.let {
                        updateView(it)
                    }
                }
            }
    }

    private fun updateView(media: ArrayList<MediaDetails>) {
        clearLists()
        updateLists(media)
        (binding.rvSearchResults.adapter as SearchMediaAdapter).submitList(mediaTypes)
    }

    private fun updateLists(media: ArrayList<MediaDetails>) {
        moviesList = Utils.getMoviesList(media)
        tvShowList = Utils.getTvShowsList(media)
        otherList = Utils.getOtherList(media)
        if (moviesList.isNotEmpty()) {
            mediaTypes.add(MediaType.Movie(moviesList))
        }
        if (tvShowList.isNotEmpty()) {
            mediaTypes.add(MediaType.TVShow(tvShowList))
        }
        if (otherList.isNotEmpty()) {
            mediaTypes.add(MediaType.Other(otherList))
        }
    }

    private fun setupRecyclerView() {
        val searchMediaAdapter = SearchMediaAdapter(this)
        binding.rvSearchResults.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = searchMediaAdapter
        }
    }

    private fun clearLists() {
        moviesList.clear()
        tvShowList.clear()
        otherList.clear()
        mediaTypes.clear()
    }

    override fun onBackPressed() {
        if (binding.searchView.isShowing) {
            handleSearchViewBackPress()
        } else {
            super.onBackPressed()
        }
    }

    private fun handleSearchViewBackPress() {
        binding.searchView.handleBackInvoked()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onMediaItemClick(mediaDetail: MediaDetails) {
        mediaDetail.toMediaDetail().also {
            lifecycleScope.launch(Dispatchers.IO) {
                StarzplayApp.database?.mediaDao()?.insertSearchedItem(it)
            }
        }


        val intent = Intent(this@MainActivity, MediaDetailsActivity::class.java)
        intent.putExtra(AppConstants.MEDIA_DETAIL_OBJ_KEY, mediaDetail)
        startActivity(intent)
    }
}
