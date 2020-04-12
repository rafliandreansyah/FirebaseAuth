package com.azhara.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        firebaseAuth = FirebaseAuth.getInstance()

        btn_send_link.setOnClickListener {
            val email = send_email.text.toString().trim()

            if (!email.isEmpty()){
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Silahkan cek email untuk reset password",
                            Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}
