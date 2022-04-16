package com.gunish.newsbreeze.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.gunish.newsbreeze.databinding.ActivityHomeBinding
import com.gunish.newsbreeze.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewModel,ActivityHomeBinding>() {

    override val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
    }

    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)

}