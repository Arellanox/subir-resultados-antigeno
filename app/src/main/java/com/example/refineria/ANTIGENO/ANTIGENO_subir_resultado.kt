package com.example.refineria.ANTIGENO

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.refineria.R
import kotlinx.android.synthetic.main.activity_antigeno_subir_resultado.*
import kotlinx.android.synthetic.main.dialog_resultado.view.*

class ANTIGENO_subir_resultado : AppCompatActivity() {

    var nombre_label:TextView? = null
    var procedencia_label:TextView?= null
    var fecha_registro_label:TextView?= null
    var camper_label:TextView? = null
    var sexo_label:TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antigeno_subir_resultado)

        val bundle = intent.extras
        val id_paciente = bundle?.getInt("id_paciente")
        val nombre = bundle?.getString("nombre")
        val segmento = bundle?.getString("segmento")
        val sexo = bundle?.getString("sexo")
        val fechaIngreso = bundle?.getString("fechaIngreso")
        val origen = bundle?.getString("origen")
        val procedencia = bundle?.getString("procedencia")
        val edad = bundle?.getInt("edad")

        nombre_label = findViewById(R.id.textNombrePasiente)
        procedencia_label = findViewById(R.id.textProcedencia)
        fecha_registro_label = findViewById(R.id.textFechaIngreso)
        camper_label =findViewById(R.id.textCamper)
        sexo_label = findViewById((R.id.textSexo))

        nombre_label?.text = nombre
        procedencia_label?.text = procedencia
        fecha_registro_label?.text = fechaIngreso
        camper_label?.text = origen
        sexo_label?.text = sexo

        //Enviar resultado
        PositivoButton.setOnClickListener {
            alertDialog("Positivo", nombre,edad.toString(),procedencia, segmento, id_paciente)
        }
        NegativoButton.setOnClickListener {
            alertDialog("Negativo", nombre,edad.toString(),procedencia, segmento, id_paciente)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun alertDialog(resultado:String, nombre: String?,edad:String?, procedencia:String?, segmento: String?, id_paciente: Int?){

        //Asignar valores del dialog
        val builder = AlertDialog.Builder(this@ANTIGENO_subir_resultado)
        val view = layoutInflater.inflate(R.layout.dialog_resultado, null)

        //Colocar valores al text
        view.findViewById<TextView>(R.id.txtDialogAviso).text = "¿Desea agregar este paciente como $resultado?"
        view.findViewById<TextView>(R.id.DialogResultado).text = resultado
        view.findViewById<TextView>(R.id.txtDialogNombrePaciente).text = nombre
        view.findViewById<TextView>(R.id.txtDialogProcedencia).text = "${procedencia} - ${segmento}"
        view.findViewById<TextView>(R.id.txtDialogEdad).text = edad
        //Pasando vista al builder
        builder.setView(view)

        //creando el dialog
        val dialog = builder.create()
        dialog.show()

        view.btnDialogAceptar.setOnClickListener {
            Toast.makeText(this, "A seleccionado el ${resultado}, por favor termine la siguiente información", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ANTIGENO_resultado_formulario::class.java).apply {
                putExtra("RESULTADO", resultado)
                putExtra("NOMBRE", nombre)
                putExtra("ID_PACIENTE", id_paciente)
            }
            this.startActivity(intent)
            finish()
            dialog.dismiss()
        }

        view.btnDialogCancelar.setOnClickListener {
            dialog.dismiss()
        }
    }
}