package com.repos.repoassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.repos.repoassignment.module.newsList.NewsListActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        CoroutineScope(Dispatchers.Main).launch {

            delay(3000)
            val intent = Intent(this@SplashActivity, NewsListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}