package com.fundtastic.mymovie.view.ui.details.tv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import com.fundtastic.mymovie.R
import com.fundtastic.mymovie.compose.ViewModelActivity
import com.fundtastic.mymovie.databinding.ActivityTvDetailBinding
import com.fundtastic.mymovie.extension.applyToolbarMargin
import com.fundtastic.mymovie.extension.simpleToolbarWithHome
import com.fundtastic.mymovie.models.entity.Tv
import com.fundtastic.mymovie.view.adapter.ReviewListAdapter
import com.fundtastic.mymovie.view.adapter.VideoListAdapter
import com.skydoves.whatif.whatIfNotNull
import kotlinx.android.synthetic.main.activity_tv_detail.*

class TvDetailActivity : ViewModelActivity() {

    private val viewModel: TvDetailViewModel by injectViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding<ActivityTvDetailBinding>(R.layout.activity_tv_detail).run {
            lifecycleOwner = this@TvDetailActivity
            viewModel = this@TvDetailActivity.viewModel.apply { postTvId(getTvFromIntent().id) }
            tv = getTvFromIntent()
            videoAdapter = VideoListAdapter()
            reviewAdapter = ReviewListAdapter()
        }
        initializeUI()
    }

    private fun initializeUI() {
        applyToolbarMargin(tv_detail_toolbar)
        simpleToolbarWithHome(tv_detail_toolbar, getTvFromIntent().name)
    }

    private fun getTvFromIntent() = intent.getParcelableExtra<Parcelable>(tvId) as Tv

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return false
    }

    companion object {
        private const val tvId = "tv"
        fun startActivityModel(context: Context?, tv: Tv) {
            context.whatIfNotNull {
                val intent = Intent(it, TvDetailActivity::class.java).apply { putExtra(tvId, tv) }
                it.startActivity(intent)
            }
        }
    }
}
