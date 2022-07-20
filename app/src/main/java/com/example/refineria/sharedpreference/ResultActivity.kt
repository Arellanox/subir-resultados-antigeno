package com.example.refineria.sharedpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refineria.R
import com.example.refineria.sharedpreference.RefineriaApplication.Companion.prefs
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        iniUI()

    }

    fun iniUI(){
        button.setOnClickListener {
            prefs.wipe()
            onBackPressed()
        }
        val userName = prefs.getName()
        textView18.text = "Bienvenido $userName"

    }
}