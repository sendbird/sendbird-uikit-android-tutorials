package com.sendbird.uikit.tutorial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val userId = findViewById<EditText>(R.id.userIdEditText).text.toString()
            getSharedPreferences("sendbird", MODE_PRIVATE).edit().putString("user_id", userId).apply()
            startActivity(Intent(this@LoginActivity, OrderStatusActivity::class.java))
        }
    }
}
