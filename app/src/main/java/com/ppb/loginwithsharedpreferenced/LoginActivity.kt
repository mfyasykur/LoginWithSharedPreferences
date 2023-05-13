package com.ppb.loginwithsharedpreferenced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ppb.loginwithsharedpreferenced.connection.DBHelper

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()

        if (savedLogin.getString("Status", "Off") == "On") {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val editUsername: EditText = findViewById(R.id.edit_username_login)
        val editPassword: EditText = findViewById(R.id.edit_password_login)
        val btnLogin: Button = findViewById(R.id.btn_login)
        val btnRegister: Button = findViewById(R.id.btn_register)
        val userDBHelper = DBHelper(this)

        btnLogin.setOnClickListener {
            var emailku = editUsername.text.toString()
            var passwordku = editPassword.text.toString()
            var cekLogin = userDBHelper.cekLogin(emailku, passwordku)

            if (cekLogin == "1") {
                editSavedLogin.putString("Email", emailku)
                editSavedLogin.putString("Password", passwordku)
                editSavedLogin.putString("Status", "On")
                editSavedLogin.commit()

                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)
            } else {
                val toast: Toast = Toast.makeText(applicationContext, "Gagal Login", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)

            startActivity(intent)
        }
    }
}