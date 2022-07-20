package com.example.refineria.monitoreo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refineria.R
import kotlinx.android.synthetic.main.activity_monitoreo_cuestionario_formulario.*

class MONITOREO_Cuestionario_Formulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoreo_cuestionario_formulario)
        setupListener()


        backHome.setOnClickListener{
            finish()
        }

    }

    private fun setupListener(){

    }
}