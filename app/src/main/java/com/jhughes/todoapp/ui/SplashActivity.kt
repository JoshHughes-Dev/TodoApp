package com.jhughes.todoapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import com.jhughes.todoapp.R

class SplashActivity : AppCompatActivity() {

    lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler = Handler(Looper.getMainLooper())
    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed({ startMainActivity() }, 3000);
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
    }
}
