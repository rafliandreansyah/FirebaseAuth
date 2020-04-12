package com.azhara.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.register -> startActivity(Intent(this, RegisterActivity::class.java))

            R.id.btn_login -> {

            }
        }
    }
}
