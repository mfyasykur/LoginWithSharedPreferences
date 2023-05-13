package com.ppb.loginwithsharedpreferenced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ppb.loginwithsharedpreferenced.connection.DBHelper

class RegisterActivity : AppCompatActivity() {

    lateinit var email_reg: EditText
    lateinit var password_reg: EditText
    lateinit var fullname_reg: EditText
    lateinit var btnRegister: Button
    lateinit var btnCancel: Button
    lateinit var userDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        email_reg = findViewById(R.id.edit_email_register)
        password_reg = findViewById(R.id.edit_password_register)
        fullname_reg = findViewById(R.id.edit_fullname_register)
        btnRegister = findViewById(R.id.btn_submit_register)
        btnCancel = findViewById(R.id.btn_cancel_register)
        userDBHelper = DBHelper(this)
    }

    fun registerMe(view: View) {

        var emailMe = email_reg.text.toString()
        var passwordMe = password_reg.text.toString()
        var fullnameMe = fullname_reg.text.toString()

        var cekUser = userDBHelper.cekUser(emailMe)
        var status = "Gagal"

        if (cekUser == "0") {
            userDBHelper.RegisterUser(emailMe, passwordMe, fullnameMe)
            status = "Sukses"
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }

        val toast: Toast = Toast.makeText(applicationContext, status, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun cancelMe(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}