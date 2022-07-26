package com.example.refineria.classes

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.refineria.network.MySingleton
import com.example.refineria.sharedpreference.RefineriaApplication.Companion.prefs
import org.json.JSONObject

class PacientesAntigenoProvider(var lista: List<PacientesAntigeno> = listOf<PacientesAntigeno>()) {
    var estadoLista:Int? = null

    init {
        this.estadoLista = 0
    }

    fun obtenerListaPacientesAntigeno(context : Context) {
        //val url = "https://bimo-lab.com/movil/antigenos/api/antigenos_obtener_lista.php"
        val url = prefs.getAntObtenerLista()
        val data = JSONObject()
        data.put("usuario", prefs.getIdUsuario())
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,data,{
                response ->
            val json = response.getJSONObject("response")
            val code = json.getInt("code")
            if(code==1){
                lista = emptyList()
                val datos = json.getJSONArray("datos")
                for (i in 0..datos.length()-1){
                    val paciente = datos.getJSONObject(i)
                    lista += PacientesAntigeno(
                        paciente.getInt("id_paciente"),
                        paciente.getString("folio"),
                        paciente.getString("folioOrden"),
                        paciente.getString("nombre"),
                        paciente.getString("resultado"),
                        paciente.getString("fechaResultado"),
                        paciente.getString("prefolio"),
                        paciente.getString("procedencia"),
                        paciente.getString("sexo"),
                        paciente.getString("fechaIngreso"),
                        paciente.getString("indicador"),
                        paciente.getString("origen"),
                        paciente.getString("lugarextra"),
                        paciente.getString("procedenciapdf"),
                        paciente.getInt("edad"))
                }
                cambiarEstadoLista()
            }else{
                cambiarEstadoLista()
                Toast.makeText(context,"Error: Inicie sesión nuevamente", Toast.LENGTH_LONG).show()
            }
            Log.d("fin de la funcion volley","HA TERMINADO LA FUNCION QUE CARGA LA VISTA PRINCIPAL")
            Log.d("lista de la clase", lista.toString())
        },{
            error ->
            Log.d("error volley lista",error.toString())
        })
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    fun cambiarEstadoLista(){
        this.estadoLista = 1
        //return this.estadoLista!!
    }

    fun recuperarEstadoLista():Int{
        return this.estadoLista!!
    }
}