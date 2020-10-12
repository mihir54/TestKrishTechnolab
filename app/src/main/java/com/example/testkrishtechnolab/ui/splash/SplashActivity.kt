package com.example.testkrishtechnolab.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.testkrishtechnolab.R
import com.example.testkrishtechnolab.ui.login.LoginActivity
import com.example.testkrishtechnolab.ui.product.ProductActivity
import com.example.testkrishtechnolab.utils.common.Event
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val splashViewModel : SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModel.onCreate()
        setupObservers()
    }


    private fun setupObservers() {
        splashViewModel.launchLogin.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
            }
        })

        splashViewModel.launchMain.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, ProductActivity::class.java))
            }
        })
    }
}