package com.example.islamy_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.islamy_project.databinding.ActivtySplashBinding

class SplashActivity :AppCompatActivity() {
    lateinit var viewbinding:ActivtySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       viewbinding=ActivtySplashBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)

    }
}