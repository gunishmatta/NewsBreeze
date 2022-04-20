package com.gunish.newsbreeze.ui.saved_news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.gunish.newsbreeze.R
import com.gunish.newsbreeze.data.local.entity.News
import com.gunish.newsbreeze.databinding.ActivitySavedNewsBinding
import com.gunish.newsbreeze.ui.base.BaseActivity
import com.gunish.newsbreeze.ui.home.HomeViewModel
import com.gunish.newsbreeze.ui.saved_news.adapter.SaveNewsAdapter

class SavedNews : BaseActivity<HomeViewModel,ActivitySavedNewsBinding>() {

    override val viewModel: HomeViewModel by viewModels()
    var saveNewsAdapter: SaveNewsAdapter? = null

    var newsList:ArrayList<News> = ArrayList()
    var tempList:ArrayList<News> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

    }

    override fun getViewBinding(): ActivitySavedNewsBinding = ActivitySavedNewsBinding.inflate(layoutInflater)
}