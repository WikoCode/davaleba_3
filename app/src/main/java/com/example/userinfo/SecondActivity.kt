package com.example.userinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.userinfo.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val username = intent.getStringExtra("username")
        val age = intent.getStringExtra("age")

        val userInfo = binding.UserInfo
        val displayInfo = "Email: $email\nFull Name: ${firstName + " " + lastName}\nUsername: $username\nAge: $age"
        userInfo.text = displayInfo

        val intent2 = Intent(this, MainActivity::class.java)

        val againButton = binding.btnAgain
        againButton.setOnClickListener() {
            startActivity(intent2)
            finish()
        }
    }







}