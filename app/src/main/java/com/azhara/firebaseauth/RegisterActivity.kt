package com.azhara.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        if (supportActionBar != null){
            supportActionBar?.title = "Register"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }
}
