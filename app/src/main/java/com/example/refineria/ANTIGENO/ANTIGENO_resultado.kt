package com.example.refineria.ANTIGENO

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.refineria.MainActivity
import com.example.refineria.R
import com.example.refineria.classes.historial
import kotlinx.android.synthetic.main.activity_antigeno_resultado.*
import kotlinx.android.synthetic.main.dialog_resultado.view.*

class ANTIGENO_resultado : AppCompatActivity() {
    var nombre_label: TextView? = null
    var procedencia_label: TextView?= null
    var fecha_registro_label: TextView?= null
    var camper_label: TextView? = null
    var sexo_label: TextView? = null
    val a = historial()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antigeno_resultado)


        val bundle = intent.extras
        val id_paciente = bundle?.getInt("id_paciente")
        val nombre = bundle?.getString("nombre")
        val segmento = bundle?.getString("segmento")
        val sexo = bundle?.getString("sexo")
        val fechaIngreso = bundle?.getString("fechaIngreso")
        val origen = bundle?.getString("origen")
        val procedencia = bundle?.getString("procedencia")
        val edad = bundle?.getInt("edad")


        a.historialapp(this,"Cargó la vista de subir resultado del paciente ${nombre} (${id_paciente}) en android")

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

        backHome.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            a.historialapp(this,"Canceló subir resultado del paciente ${textNombrePasiente.text} (${textFechaIngreso.text}) en android")
            finish()
        }

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
        val builder = AlertDialog.Builder(this@ANTIGENO_resultado)
        val view = layoutInflater.inflate(R.layout.dialog_resultado, null)

        //Colocar valores al text
        val txtResultado = view.findViewById<TextView>(R.id.txtDialogResultado)
        view.findViewById<TextView>(R.id.txtDialogAviso).text = "¿Desea agregar el resultado a este pacientes?"
        txtResultado.text = resultado
        if (resultado == "Positivo"){
            txtResultado.setTextColor(Color.RED)
        }else{
            txtResultado.setTextColor(Color.GREEN)
        }
        view.findViewById<TextView>(R.id.DialogResultado).text = resultado
        view.findViewById<TextView>(R.id.txtDialogNombrePaciente).text = nombre
        //view.findViewById<TextView>(R.id.txtDialogProcedencia).text = "${procedencia} - ${segmento}"
        view.findViewById<TextView>(R.id.txtDialogEdad).text = "${edad} años"
        //Pasando vista al builder
        builder.setView(view)

        //creando el dialog
        val dialog = builder.create()
        dialog.show()

        view.btnDialogAceptar.setOnClickListener {
            Toast.makeText(this, "A seleccionado el ${resultado}, por favor termine la siguiente información", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ANTIGENO_resultado_formulario::class.java).apply {
                putExtra("RESULTADO", resultado)
                putExtra("NOMBRE", nombre)
                putExtra("ID_PACIENTE", id_paciente)
            }
            this.startActivity(intent)
            finish()
            dialog.dismiss()
            a.historialapp(this,"Subirá el resultado como ${resultado} del paciente ${nombre} (${textFechaIngreso.text}) en android")
        }

        //view.btnDialogCancelar.setOnClickListener {
        //    dialog.dismiss()
        //}
    }

    override fun onBackPressed() {
        a.historialapp(this,"Canceló subir resultado del paciente ${textNombrePasiente.text} (${textFechaIngreso.text}) en android")
        finish()
    }
}