package com.example.userinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.view.GestureDetector
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.userinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var saveButton: View
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        usernameFocusListener()
        ageFocusListener()
        firstnameFocusListener()
        lastnameFocusListener()

        val clearButton = binding.btnClear
        clearButton.setOnLongClickListener {
            clearEditText(binding.emailEditText)
            clearEditText(binding.usernameEditText)
            clearEditText(binding.ageEditText)
            clearEditText(binding.firstnameEditText)
            clearEditText(binding.lastnameEditText)

            true}

        val saveButton = binding.btnSave

        saveButton.setOnClickListener {
            if (validateFields()) {
                saveDataAndNavigateToSecondActivity()
            } else {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Validation Error")
                alertDialog.setMessage("Please check your input and try again.")
                alertDialog.setPositiveButton("OK", null)
                alertDialog.show()
            }
        }

    }

    private fun validateFields(): Boolean {
        val email = binding.emailEditText.text.toString()
        val username = binding.usernameEditText.text.toString()
        val ageText = binding.ageEditText.text.toString()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }

        if (username.length < 10) {
            return false
        }

        if (ageText.isEmpty()) {
            return false
        }

        val age = ageText.toInt()
        if (age <= 0 || age >= 150) {
            return false
        }

        return true
    }

    private fun saveDataAndNavigateToSecondActivity() {
        val email = binding.emailEditText.text.toString()
        val firstName = binding.firstnameEditText.text.toString()
        val lastName = binding.lastnameEditText.text.toString()
        val username = binding.usernameEditText.text.toString()
        val age = binding.ageEditText.text.toString()


        val intent = Intent(this, SecondActivity::class.java)



        intent.putExtra("email", email)
        intent.putExtra("firstName", firstName)
        intent.putExtra("lastName", lastName)
        intent.putExtra("username", username)
        intent.putExtra("age", age)



        startActivity(intent)
        finish()
    }



    private fun clearEditText(editText: EditText) {
        editText.text.clear()
    }

    private fun emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener{ _ , focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText =  binding.emailEditText.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email"
        }

        return null
    }

    private fun usernameFocusListener() {
        binding.usernameEditText.setOnFocusChangeListener{ _ , focused ->
            if (!focused) {
                binding.usernameContainer.helperText = validUsername()
            }
        }
    }

    private fun validUsername(): String? {
        val usernameText =  binding.usernameEditText.text.toString()
        if(usernameText.length < 10){
            return "Username must be at least 10 characters"
        }

        return null
    }

    private fun ageFocusListener() {
        binding.ageEditText.setOnFocusChangeListener{ _ , focused ->
            if(!focused) {
                binding.ageContainer.helperText = validAge()
            }
        }
    }

    private fun validAge(): String? {
        val ageInt = binding.ageEditText.text.toString().toInt()
        if (ageInt <= 0 ) {
            return "Insert a valid age please"
        }

        return null
    }

    private fun firstnameFocusListener() {
        binding.firstnameEditText.setOnFocusChangeListener{ _ , focused ->
            if (!focused) {
                binding.firstnameContainer.helperText = validFirstname()
            }
        }
    }

    private fun validFirstname(): String? {
        val firstnameText =  binding.firstnameEditText.text.toString()
        if(firstnameText.isEmpty()) {
            return "Insert First Name"
        }

            return null
    }

    private fun lastnameFocusListener() {
        binding.lastnameEditText.setOnFocusChangeListener{ _ , focused ->
            if (!focused) {
                binding.lastnameContainer.helperText = validLastname()
            }
        }
    }

    private fun validLastname(): String? {
        val firstnameText =  binding.lastnameEditText.text.toString()
        if(firstnameText.isEmpty()) {
            return "Insert Last Name"
        }

        return null
    }




}