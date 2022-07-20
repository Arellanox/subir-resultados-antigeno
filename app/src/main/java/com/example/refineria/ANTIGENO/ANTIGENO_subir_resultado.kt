package com.example.refineria.ANTIGENO

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.refineria.R
import kotlinx.android.synthetic.main.activity_antigeno_subir_resultado.*

class ANTIGENO_subir_resultado : AppCompatActivity() {
    val bundle = intent.extras
    val numero = bundle?.getString("NUMERO")
    val nombre = bundle?.getString("NOMBRE")
    val prefolio = bundle?.getString("PREFOLIO")

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antigeno_subir_resultado)





        //Enviar resultado
        PositivoButton.setOnClickListener {
            form("Positivo")
        }
        NegativoButton.setOnClickListener {
            form("Negativo")
        }

    }
    fun form(resultado:String){
        val intent = Intent(this, ANTIGENO_resultado_formulario::class.java).apply {
            putExtra("NUMERO", numero)
            putExtra("NOMBRE", nombre)
            putExtra("PREFOLIO", prefolio)
            putExtra("RESULTADO", resultado)
        }
        this.startActivity(intent)
        Toast.makeText(this, "Se a seleccionado: $resultado", Toast.LENGTH_SHORT).show()

    }
}