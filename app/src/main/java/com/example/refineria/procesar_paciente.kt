package com.example.refineria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.refineria.ANTIGENO.ANTIGENO_consultar_resultado
import com.example.refineria.ANTIGENO.ANTIGENO_subir_resultado
import com.example.refineria.classes.PacientesAntigeno
import com.example.refineria.monitoreo.MONITOREO_Cuestionario_Formulario

class procesar_paciente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procesar_paciente)

        val bundle = intent.extras
        val id_paciente = bundle?.getInt("id_paciente")
        val folio = bundle?.getString("folio")
        val folioOrden = bundle?.getString("folioOrden")
        val nombre = bundle?.getString("nombre")
        val resultado = bundle?.getString("resultado")
        val fechaResultado = bundle?.getString("fechaResultado")
        val prefolio = bundle?.getString("prefolio")
        val segmento = bundle?.getString("segmento")
        val sexo = bundle?.getString("sexo")
        val fechaIngreso = bundle?.getString("fechaIngreso")
        val indicador = bundle?.getString("indicador")
        val origen = bundle?.getString("origen")
        val lugarExtra = bundle?.getString("lugarExtra")
        val procedencia = bundle?.getString("procedencia")
        val edad = bundle?.getInt("edad")

        if (resultado == "null"){
            val intent = Intent(this, ANTIGENO_subir_resultado::class.java).apply {
                putExtra("id_paciente", id_paciente)
                putExtra("nombre", nombre)
                putExtra("segmento", segmento)
                putExtra("sexo", sexo)
                putExtra("fechaIngreso", fechaIngreso)
                putExtra("origen", origen)
                putExtra("lugarExtra", lugarExtra)
                putExtra("procedencia", procedencia)
                putExtra("edad", edad)
            }
            this.startActivity(intent)
        }else{
            val intent = Intent(this, ANTIGENO_consultar_resultado::class.java).apply {
                putExtra("id_paciente", id_paciente)
                putExtra("NOMBRE", nombre)
            }
            Toast.makeText(this,"Paciente: ${id_paciente}", Toast.LENGTH_SHORT).show()
            this.startActivity(intent)
        }

        finish()
    }
}