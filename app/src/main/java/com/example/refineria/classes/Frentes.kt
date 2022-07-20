package com.example.refineria.classes

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.refineria.database.SQLite
import com.example.refineria.network.MySingleton
import org.json.JSONArray

class Frentes {

    fun downloadFrentes(context: Context){
        val url = "http://bimotest.com/api/monitoreo_frente_get_all.php"
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,url,null,
            {
                    response ->
                try {
                    this.dropTable(context)
                    this.createTable(context)
                    insertFrentes(context, response)
                }catch (e: Exception){
                    Log.d("ERROR",e.toString())
                }
            },
            {
                    error ->
                Toast.makeText(context,"Ha ocurrido un error ${error}", Toast.LENGTH_LONG).show()
            }
        )
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    fun dropTable(context: Context){
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        database.execSQL("DROP TABLE IF EXISTS monitoreo_frentes")
    }

    fun createTable(context: Context){
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        database.execSQL("CREATE TABLE monitoreo_frentes(id_frente int primary key," +
                "nombre_frente text," +
                "numero_frente text," +
                "supervisor_obra text," +
                "supervisor_seguridad text," +
                "turno text," +
                "activo int," +
                "ubicacion text," +
                "predio text);")
    }

    fun insertFrentes(context: Context,json: JSONArray?){
        Toast.makeText(context,"Descargando Frentes...",Toast.LENGTH_LONG).show()
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        if (json!=null){
            for (i in 0..json.length()-1){
                val row = json.getJSONObject(i)
                var insert = ContentValues().apply {
                    put("id_frente",row.getInt("ID_FRENTE"))
                    put("nombre_frente",row.getString("NOMBRE_FRENTE"))
                    put("numero_frente",row.getString("NUMERO_FRENTE"))
                    put("supervisor_obra",row.getString("SUPERVISOR_OBRA"))
                    put("supervisor_seguridad",row.getString("SUPERVISOR_SEGURIDAD"))
                    put("turno",row.getString("TURNO"))
                    put("activo",row.getInt("ACTIVO"))
                    put("ubicacion",row.getString("UBICACION"))
                    put("predio",row.getString("PREDIO"))
                }
                database.insert("monitoreo_frentes",null,insert)
                Log.d("FRENTES DESCARGADOS",row.toString())
            }
        }
    }
}