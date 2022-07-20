package com.example.refineria.ANTIGENO

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refineria.R
import kotlinx.android.synthetic.main.activity_antigeno_resultado_formulario.*

class ANTIGENO_resultado_formulario : AppCompatActivity() {
    val bundle = intent.extras
    val numero = bundle?.getString("NUMERO")
    val nombre = bundle?.getString("NOMBRE")
    val prefolio = bundle?.getString("PREFOLIO")
    val resultado = bundle?.getString("RESULTADO")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antigeno_resultado_formulario)

        buttonprueba.setOnClickListener {

        }
    }
}