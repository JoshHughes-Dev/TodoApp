package com.jhughes.todoapp.ui

import android.os.Bundle
import com.jhughes.todoapp.injection.scopedItems.BaseActivityItem
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject lateinit var baseActivityItem: BaseActivityItem

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}