package com.example.refineria.ANTIGENO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refineria.R

class ANTIGENO_subir_resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antigeno_subir_resultado)

        val bundle = intent.extras
        val numero = bundle?.getString("NUMERO")
        val nombre = bundle?.getString("NOMBRE")
        val prefolio = bundle?.getString("PREFOLIO")



    }
}