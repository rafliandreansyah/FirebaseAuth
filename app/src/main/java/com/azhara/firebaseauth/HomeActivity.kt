package com.azhara.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val EXTRA_ID = "ID"
        const val EXTRA_EMAIL = "EMAIL"
        const val EXTRA_VERIFICATION = "VERIFICATION"
    }

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        btn_logout.setOnClickListener(this)
        btn_delete.setOnClickListener(this)
        btn_verification.setOnClickListener(this)

        val userId = user?.uid.toString()
        val userEmail = user?.email.toString()
        val userVerification = user?.isEmailVerified.toString()

        text_profile.text = "User id: $userId \nUserEmail: $userEmail \nuserVerification: $userVerification"
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_logout -> {
                logout()
            }
            R.id.btn_verification -> {
                emailVerification()
            }
        }
    }

    private fun logout(){
        firebaseAuth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun emailVerification(){
        val user = firebaseAuth.currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Cek email untuk verifikasi!!", Toast.LENGTH_SHORT).show()
                Log.d(HomeActivity::class.java.name, "${task.isSuccessful}")
            }
        }
    }
}
