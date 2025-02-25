package com.example.experimentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)*/

        val gotoStickySectionButton = findViewById<MaterialButton>(R.id.buttonGotoStickySection)
        val gotoSearchScreenButton = findViewById<MaterialButton>(R.id.buttonGotoSearchScreen)

        gotoStickySectionButton.setOnClickListener {
            startActivity(Intent(this, StickySectionActivity::class.java))
        }

        gotoSearchScreenButton.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}
