package com.jhughes.todoapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.view.Window
import com.jhughes.todoapp.R



class SplashActivity : AppCompatActivity() {

    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.enterTransition = Explode()

        setContentView(R.layout.activity_splash)

        handler = Handler(Looper.getMainLooper())

    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed({ startMainActivity() }, 1500);
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
    }
}
