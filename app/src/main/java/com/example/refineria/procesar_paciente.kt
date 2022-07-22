package com.example.refineria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refineria.ANTIGENO.ANTIGENO_subir_resultado
import com.example.refineria.monitoreo.MONITOREO_Cuestionario_Formulario

class procesar_paciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procesar_paciente)

        val bundle = intent.extras
        val numero = bundle?.getString("NUMERO")
        val nombre = bundle?.getString("NOMBRE")
        val prefolio = bundle?.getString("PREFOLIO")
        val resultado = bundle?.getString("RESULTADO")
        if (resultado == "null"){
            val intent = Intent(this, ANTIGENO_subir_resultado::class.java).apply {
                putExtra("NUMERO", numero)
                putExtra("NOMBRE", nombre)
                putExtra("PREFOLIO", prefolio)
            }
            this.startActivity(intent)
        }else{
            val intent = Intent(this, MONITOREO_Cuestionario_Formulario::class.java).apply {
                putExtra("NUMERO", numero)
                putExtra("NOMBRE", nombre)
                putExtra("PREFOLIO", prefolio)
            }
            this.startActivity(intent)
        }

        finish()
    }
}