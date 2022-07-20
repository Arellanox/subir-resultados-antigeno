package com.example.refineria.classes

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.refineria.database.SQLite
import com.example.refineria.network.MySingleton
import org.json.JSONArray
import org.json.JSONObject

class Trabajadores {

    @SuppressLint("Range")
    fun getTrabajadores(context: Context): JSONArray {
        val con= SQLite(context, "Refineria", null, 1)
        val database= con.writableDatabase
        val trabajadores = database.rawQuery("select * from monitoreo_trabajadores where servidor='0'",null)
        var json = JSONArray()
        while (trabajadores.moveToNext()){
            var jsonObject = JSONObject()
            jsonObject.put("id",trabajadores.getString(trabajadores.getColumnIndex("id_trabajador")))
            jsonObject.put("nombre",trabajadores.getString(trabajadores.getColumnIndex("nombre")))
            jsonObject.put("apellidos",trabajadores.getString(trabajadores.getColumnIndex("apellidos")))
            jsonObject.put("edad", trabajadores.getInt(trabajadores.getColumnIndex("edad")))
            jsonObject.put("nacimiento",trabajadores.getString(trabajadores.getColumnIndex("fecha_nacimiento")))
            jsonObject.put("numero",trabajadores.getString(trabajadores.getColumnIndex("num_trabajador")))
            jsonObject.put("categoria",trabajadores.getString(trabajadores.getColumnIndex("categoria")))
            jsonObject.put("servidor", trabajadores.getInt(trabajadores.getColumnIndex("servidor")))
            jsonObject.put("activo",trabajadores.getInt(trabajadores.getColumnIndex("activo")))
            jsonObject.put("code",3)
            json.put(jsonObject)
        }
        Log.d("json", json.toString())
        return json
    }

    fun dropTable(context: Context){
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        database.execSQL("DROP TABLE IF EXISTS monitoreo_trabajadores")
    }

    fun createTable(context: Context){
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        database.execSQL("CREATE TABLE monitoreo_trabajadores (" +
                "id_trabajador text primary key," +
                "nombre text," +
                "apellidos text," +
                "edad int," +
                "fecha_nacimiento text," +
                "num_trabajador text," +
                "categoria text," +
                "servidor int," +
                "activo int);")
    }

    fun uploadTrabajadores(context: Context,json: JSONArray?){
        val url = "http://bimotest.com/api/monitoreo_trabajador_api.php"
        if (json!=null){
            for (i in 0..json.length()-1){
                var row = json.getJSONObject(i)
                Log.d("row", row.toString())
                val jsonObject = JsonObjectRequest(Request.Method.POST,url,row,
                    {
                        response ->
                        //Toast.makeText(context,"Trabajador: ${response.toString()}",Toast.LENGTH_LONG).show()
                        changeStateServidor(row.getString("id"),context)

                    },
                    {
                        error ->
                        Log.d("Error===>","Error al insertar trabajador: ${error.toString()}")
                    }
                )
                MySingleton.getInstance(context).addToRequestQueue(jsonObject)
            }
            downloadTrabajadores(context)
        }
    }

    fun downloadTrabajadores(context: Context){
        this.dropTable(context)
        this.createTable(context)
        val url = "http://bimotest.com/api/monitoreo_trabajador_api.php"
        var data = JSONObject()
        data.put("code",1)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,data,
            {
                response ->
                insertTrabajadoresLocal(context,response)
                Log.d("respuesta trabajadores", response.toString())
            },{
                error->
                Log.d("Error al insertar trabajador en sqlite",error.toString())
            })
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    fun insertTrabajadoresLocal(context: Context, json: JSONObject){
        var jsonArray = json.getJSONArray("trabajadores")
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        for (i in 0..jsonArray.length()-1){
            val row = jsonArray.getJSONObject(i)
            var insert = ContentValues().apply {
                put("id_trabajador",row.getString("ID_TRABAJADOR"))
                put("nombre",row.getString("NOMBRE"))
                put("apellidos",row.getString("APELLIDOS"))
                put("edad",row.getInt("EDAD"))
                put("fecha_nacimiento",row.getString("FECHA_NACIMIENTO"))
                put("num_trabajador",row.getString("NUM_TRABAJADOR"))
                put("categoria",row.getString("CATEGORIA"))
                put("servidor",1)
                put("activo",row.getInt("ACTIVO"))
            }
            database.insert("monitoreo_trabajadores",null,insert)
        }

    }

    fun changeStateServidor(id: String, context: Context){
        val con= SQLite(context, "Refineria", null, 1)
        val database= con.writableDatabase
        val trabajadores = database.rawQuery("update monitoreo_trabajadores set servidor=1 where " +
                "id_trabajador='${id}'",null)

    }
}