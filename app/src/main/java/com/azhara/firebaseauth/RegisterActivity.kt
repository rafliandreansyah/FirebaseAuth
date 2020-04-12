package com.azhara.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()

        if (supportActionBar != null){
            supportActionBar?.title = "Register"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        btn_register.setOnClickListener(this)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_register -> {
                register()
            }
        }
    }

    private fun register(){
        val email = register_email.text.toString().trim()
        val password = register_password.text.toString().trim()
        val confirmPassword = register_password_confirm.text.toString().trim()

        if (email.isEmpty()){
            register_email.error = "Email tidak boleh kosong"
            return
        }
        if (password.isEmpty()){
            register_password.error = "Password tidak boleh kosong"
            return
        }
        if (confirmPassword.isEmpty()){
            register_password_confirm.error = "Konfirmasi password tidak boleh kosong"
            return
        }

        if (confirmPassword != password){
            register_password_confirm.error = "Password tidak sama!!"
            return
        }

        if (!email.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        val user = firebaseAuth.currentUser
                        val intent = Intent(this, HomeActivity::class.java).apply {
                            putExtra(HomeActivity.EXTRA_ID, user?.uid)
                            putExtra(HomeActivity.EXTRA_EMAIL, user?.uid)
                            putExtra(HomeActivity.EXTRA_VERIFICATION, user?.isEmailVerified)
                        }
                        startActivity(intent)
                        finish()
                    }else{
                        Log.w(RegisterActivity::class.java.name, "create user failure", task.exception)
                        Toast.makeText(this, "Error register: ${task.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }
}
