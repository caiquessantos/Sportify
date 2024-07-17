package com.jvcan.sportify

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportActionBar?.hide()
    window.statusBarColor = Color.parseColor("#0A0A0A")
    Handler(Looper.getMainLooper()).postDelayed({
      val intent = Intent(this, LoginActivity::class.java)
      startActivity(intent)
      finish()
    }, 3000)
  }
}