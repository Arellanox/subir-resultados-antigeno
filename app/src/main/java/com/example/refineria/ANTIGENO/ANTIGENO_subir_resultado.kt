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
        val id_paciente = bundle?.getString("id_paciente")
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
        val origen = bundle?.getInt("origen")
        val lugarExtra = bundle?.getString("lugarExtra")
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
        camper_label?.text = origen.toString()
        sexo_label?.text = sexo

        //Enviar resultado
        PositivoButton.setOnClickListener {
            alertDialog("Positivo", nombre,edad.toString(),procedencia)
        }
        NegativoButton.setOnClickListener {
            alertDialog("Negativo", nombre,edad.toString(),procedencia)
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
    private fun alertDialog(resultado:String, nombre: String?,edad:String?, procedencia:String?){

        //Asignar valores del dialog
        val builder = AlertDialog.Builder(this@ANTIGENO_subir_resultado)
        val view = layoutInflater.inflate(R.layout.dialog_resultado, null)

        //Colocar valores al text
        view.findViewById<TextView>(R.id.txtDialogAviso).text = "¿Desea agregar este paciente como $resultado?"
        view.findViewById<TextView>(R.id.DialogResultado).text = resultado
        view.findViewById<TextView>(R.id.txtDialogNombrePaciente).text = nombre
        view.findViewById<TextView>(R.id.txtDialogProcedencia).text = procedencia
        view.findViewById<TextView>(R.id.txtDialogEdad).text = edad
        //Pasando vista al builder
        builder.setView(view)

        //creando el dialog
        val dialog = builder.create()
        dialog.show()

        view.btnDialogAceptar.setOnClickListener {
            Toast.makeText(this, "A seleccionado el resultado, agregué la siguiente información", Toast.LENGTH_SHORT).show()
            form(resultado, nombre, "99999")
            dialog.dismiss()
        }
        
        view.btnDialogCancelar.setOnClickListener {
            dialog.dismiss()
        }
    }
}