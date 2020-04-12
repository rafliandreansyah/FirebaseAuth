package com.azhara.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()

        btn_login.setOnClickListener(this)
        register.setOnClickListener(this)
        reset_password.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.register -> startActivity(Intent(this, RegisterActivity::class.java))

            R.id.btn_login -> {
                login()
            }
            R.id.reset_password -> {
                startActivity(Intent(this, ResetPasswordActivity::class.java))
            }
        }
    }

    private fun login(){
        val email = login_email.text.toString().trim()
        val password = login_password.text.toString().trim()

        if (email.isEmpty()){
            login_email.error = "Email harus diisi!!"
            return
        }
        if (password.isEmpty()){
            login_password.error = "Password harus diisi!!"
        }

        if (!email.isEmpty() && !password.isEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }else{
                        val exception = "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The password is invalid or the user does not have a password."
                        val text = "Email atau password salah!"
                        Toast.makeText(this,
                            if (task.exception.toString() == exception) {text} else{ "Please Try again"}, Toast.LENGTH_SHORT).show()

                        Log.w(MainActivity::class.java.simpleName, "Sign in fail!", task.exception)
                    }
                }
        }
    }
}
