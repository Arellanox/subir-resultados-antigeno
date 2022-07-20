package com.example.refineria.classes

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.contentcapture.ContentCaptureSession
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.refineria.MainActivity
import com.example.refineria.database.SQLite
import com.example.refineria.network.MySingleton
import org.json.JSONArray
import org.json.JSONObject

class Usuarios {

    fun downloadUsers(context: Context){
        this.dropTable(context)
        this.createTable(context)
        val url = "http://bimotest.com/api/monitoreo_usuario_get_all.php"
        val jsonObjectRequest = JsonArrayRequest(Request.Method.GET,url,null,
            {
                    response ->
                        try {
                            insertUsers(context, response)
                        }catch (e: Exception){
                            Log.d("ERROR",e.toString())
                        }
            },
            {
                    error ->
                Toast.makeText(context,"Ha ocurrido un error ${error}",Toast.LENGTH_LONG).show()
            }
        )
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)

    }

    fun startSesion(usuario: String, password: String, context: Context) {

    }

    fun insertUsers(context: Context,json: JSONArray?){
        Toast.makeText(context,"Descargando usuarios",Toast.LENGTH_LONG).show()
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        if (json!=null) {
            for (i in 0..json.length()-1){
                val row = json.getJSONObject(i)
                if (row.getString("ID_TIPO")=="Paramédico"){
                    var insert = ContentValues().apply {
                        put("id",row.getInt("ID"))
                        put("id_tipo",3)
                        put("id_empresa",row.getString("ID_EMPRESA"))
                        put("nombre",row.getString("NOMBRE"))
                        put("apellidos",row.getString("APELLIDOS"))
                        put("sexo",row.getString("SEXO"))
                        put("edad",row.getString("EDAD"))
                        put("fecha_nacimiento",row.getString("FECHA_NACIMIENTO"))
                        put("nss",row.getString("NSS"))
                        put("usuario", row.getString("USUARIO"))
                        put("password",row.getString("PASSWORD"))
                        put("turno", row.getString("TURNO"))
                        put("activo",row.getString("ACTIVO"))
                    }
                    database.insert("monitoreo_usuarios",null,insert)
                    Log.d("ID REGISTRADO",json.toString())
                }
            }
        }

    }

    fun dropTable(context: Context){
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        database.execSQL("DROP TABLE IF EXISTS monitoreo_usuarios")
    }

    fun createTable(context: Context){
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        database.execSQL("CREATE TABLE monitoreo_usuarios(" +
                "id int primary key," +
                "id_tipo int," +
                "id_empresa int," +
                "nombre text," +
                "apellidos text," +
                "sexo text," +
                "edad text," +
                "fecha_nacimiento text," +
                "nss text," +
                "usuario text," +
                "password text," +
                "turno text," +
                "activo int);")
    }
}