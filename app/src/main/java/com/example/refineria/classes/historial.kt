package com.example.refineria.classes

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.refineria.network.MySingleton
import com.example.refineria.sharedpreference.RefineriaApplication
import com.example.refineria.sharedpreference.RefineriaApplication.Companion.prefs
import kotlinx.android.synthetic.main.activity_antigeno_consultar_resultado.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.lang.String.format
import java.net.HttpURLConnection
import java.net.URL

class historial() {
    var ip: String? = null

    fun historialapp(context : Context, accion: String? ){
        //Toast.makeText(this,"boton presionado de inicio",Toast.LENGTH_LONG).show()
        val url = RefineriaApplication.prefs.getHistorialPhp()
        //val url = "https://bimo-lab.com/movil/antigenos/api/login-api.php"
        val historialAccion = JSONObject()
        historialAccion.put("usuario", prefs.getIdUsuario())
        historialAccion.put("ip", prefs.getIPUsuario())
        historialAccion.put("sistema", prefs.getModeloUsuario())
        historialAccion.put("accion", accion)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,historialAccion, {
                response ->
            //Toast.makeText(this,"respuesta: ${response.toString()}",Toast.LENGTH_LONG).show()

            val code = response.getInt("code")
            Log.d("respuesta", code.toString())

        },{
                error ->
            //.makeText(this,"Error: ${error.toString()}", Toast.LENGTH_LONG).show()
            Log.d("error volley historial",error.toString())
        })
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    fun getExternalIpAddress(context: Context) {
        val url = "https://api.ipify.org?format=json"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                prefs.saveIPUsuario(response.getString("ip"))
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    fun modeloTelefono(){
        val modelo = "${android.os.Build.MANUFACTURER} | ${android.os.Build.MODEL} | ${android.os.Build.DEVICE}"
        prefs.saveModeloUsuario(modelo)
    }
}