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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antigeno_subir_resultado)
        val bundle = intent.extras
        val numero = bundle?.getString("NUMERO")
        val nombre = bundle?.getString("NOMBRE")
        val prefolio = bundle?.getString("PREFOLIO")
        //val procedencia = bundle?.getString("PROCEDENCIA")
        //val fechaRegistro = bundle?.getString("REGISTRO")
        //val camper = bundle?.getString("CAMPER")
        //val sexo = bundle?.getString("SEXO")
        //val edad = bundle?.getString("EDAD")


        //Enviar resultado
        PositivoButton.setOnClickListener {
            alertDialog("Positivo", nombre)
        }
        NegativoButton.setOnClickListener {
            alertDialog("Negativo", nombre)
        }

    }

    private fun form(resultado:String?, nombre: String?, prefolio: String?){
        val intent = Intent(this, ANTIGENO_resultado_formulario::class.java).apply {
            putExtra("NOMBRE", nombre)
            putExtra("PREFOLIO", prefolio)
            putExtra("RESULTADO", resultado)
        }
        this.startActivity(intent)



    }

    @SuppressLint("SetTextI18n")
    private fun alertDialog(resultado:String, nombre: String?){

        //Asignar valores del dialog
        val builder = AlertDialog.Builder(this@ANTIGENO_subir_resultado)
        val view = layoutInflater.inflate(R.layout.dialog_resultado, null)

        //Colocar valores al text
        view.findViewById<TextView>(R.id.txtDialogAviso).text = "¿Desea agregar este paciente como $resultado?"
        view.findViewById<TextView>(R.id.DialogResultado).text = resultado
        view.findViewById<TextView>(R.id.txtDialogNombrePaciente).text = nombre
        //view.findViewById<TextView>(R.id.txtDialogProcedencia).text = procedencia
        //Pasando vista al builder
        builder.setView(view)

        //creando el dialog
        val dialog = builder.create()
        dialog.show()

        view.btnDialogAceptar.setOnClickListener {
            Toast.makeText(this, "A seleccionado el resultado, agregué la siguiente información", Toast.LENGTH_SHORT).show()
            form(resultado, nombre, "99999")
        }
    }
}