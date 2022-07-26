package com.example.refineria.ANTIGENO

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.refineria.R
import com.example.refineria.network.MySingleton
import com.example.refineria.sharedpreference.RefineriaApplication.Companion.prefs
import kotlinx.android.synthetic.main.activity_antigeno_consultar_resultado.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class ANTIGENO_consultar_resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_antigeno_consultar_resultado)
        val bundle = intent.extras
        val id_paciente = bundle?.getInt("id_paciente")
        val nombre = bundle?.getString("NOMBRE")
        val resultado = bundle?.getString("RESULTADO")
        txtNombreReporte.text = nombre
        txtResultadoReporte.text = resultado
        if (resultado == "Positivo"){
            txtResultadoReporte.setTextColor(Color.RED)
        }else{
            txtResultadoReporte.setTextColor(Color.GREEN)
        }
        consultarPDF(id_paciente)
        backHome.setOnClickListener { finish() }

    }

    fun consultarPDF(id_paciente: Int?){
        Toast.makeText(this,"Verifique los datos de su paciente en el reporte", Toast.LENGTH_SHORT).show()
        //var url = prefs.getURLPDF()
        var url = "http://bimotest.com/Bimo-lab_test/movil/antigenos/pdf/pdf.php"
        val arreglo = JSONObject()
        arreglo.put("id_paciente", id_paciente)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,arreglo, {
                response ->

            //Toast.makeText(this,"Respuesta", Toast.LENGTH_SHORT).show()
            //Toast.makeText(this,"respuesta: ${response.toString()}",Toast.LENGTH_LONG).show()
            Log.d("respuesta",response.toString())
            val json = response.getJSONObject("response");
            val codigo = json.getInt("code")

            if (codigo==1){
                var urlPdf = json.getString("datos")
                //Toast.makeText(this,"Respuesta: ${urlPdf}", Toast.LENGTH_SHORT).show()
                txtURLRespuesta.text = urlPdf

            }else{
                //Toast.makeText(this,"Error: PDF no encontrado", Toast.LENGTH_LONG).show()
            }

        },{
                error ->
            Toast.makeText(this,"Error: ${error.toString()}", Toast.LENGTH_LONG).show()
            Log.d("error volley",error.toString())
        })
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }
}