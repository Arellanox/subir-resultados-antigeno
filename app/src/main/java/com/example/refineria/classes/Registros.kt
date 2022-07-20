package com.example.refineria.classes

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.refineria.database.SQLite
import com.example.refineria.network.MySingleton
import org.json.JSONArray
import org.json.JSONObject

class Registros {

    @SuppressLint("Range")
    fun getRegistros(context: Context): JSONArray{
        val con= SQLite(context, "Refineria", null, 1)
        val database= con.writableDatabase
        var registros = database.rawQuery("select * from monitoreo_registro where server=0", null)
        var json = JSONArray()
        while (registros.moveToNext()){
            var item = JSONObject()
            item.put("id",registros.getString(registros.getColumnIndex("id_registro")))
            item.put("frente",registros.getInt(registros.getColumnIndex("id_frente")))
            item.put("trabajador",registros.getString(registros.getColumnIndex("id_trabajador")))
            item.put("paramedico",registros.getInt(registros.getColumnIndex("paramedico")))
            item.put("ta", registros.getString(registros.getColumnIndex("ta")))
            item.put("tf",registros.getString(registros.getColumnIndex("tf")))
            item.put("temperatura",registros.getString(registros.getColumnIndex("temperatura")))
            item.put("oximetria",registros.getString(registros.getColumnIndex("oximetria")))
            item.put("alcoholimetria",registros.getString(registros.getColumnIndex("alcoholimetria")))
            item.put("doping",registros.getString(registros.getColumnIndex("doping")))
            item.put("otros",registros.getString(registros.getColumnIndex("otros")))
            item.put("apto", registros.getString(registros.getColumnIndex("apto")))
            item.put("fecha", registros.getString(registros.getColumnIndex("fecha_registro")))
            item.put("code",3)
            json.put(item)
        }
        Log.d("este es el json que crea:",json.toString())
        return json
    }

    fun uploadRegistros(context: Context, json: JSONArray?){
        Toast.makeText(context,"Subiendo información...", Toast.LENGTH_LONG).show()
        val url = "http://bimotest.com/api/monitoreo_registro_api.php"
        if (json!=null){
            for(i in 0..json.length()-1){
                var row = json.getJSONObject(i)
                Log.d("este es json que envia al servidor========",row.toString())
                val jsonObject = JsonObjectRequest(Request.Method.POST,url,row,
                    {
                            response ->
                        //Toast.makeText(context,"Registro Monitoreo: ${response.toString()}",Toast.LENGTH_LONG).show()
                        Log.d("esta es la respuesta del servidor en la tabla de registro:", response.toString())
                    },
                    {
                            error ->
                        Log.d("Error===>","Error al insertar registro de monitoreo: ${error.toString()}")
                    }
                )
                MySingleton.getInstance(context).addToRequestQueue(jsonObject)
            }
        }

    }
}