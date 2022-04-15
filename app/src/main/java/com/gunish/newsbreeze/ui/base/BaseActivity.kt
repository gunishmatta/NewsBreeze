package com.gunish.newsbreeze.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM:ViewModel,VB:ViewBinding>:AppCompatActivity() {

    protected abstract val viewModel:VM

    protected lateinit var mViewBinding:VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding=getViewBinding()
    }
    abstract fun getViewBinding(): VB
}