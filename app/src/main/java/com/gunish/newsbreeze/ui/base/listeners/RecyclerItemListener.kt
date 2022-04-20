package com.gunish.newsbreeze.ui.base.listeners

import com.gunish.newsbreeze.data.local.entity.News

interface RecyclerItemListener {
    fun onItemSelected(news:News)
}