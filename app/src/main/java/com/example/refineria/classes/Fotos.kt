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

class Fotos {

    @SuppressLint("Range")
    fun getFotos(context: Context): JSONArray{
        val con= SQLite(context, "Refineria", null, 1)
        val database= con.writableDatabase
        var fotos = database.rawQuery("select * from monitoreo_registro_fotos",null)
        val array = JSONArray()
        while (fotos.moveToNext()){
            var item = JSONObject()
            item.put("id",fotos.getString(fotos.getColumnIndex("id_foto")))
            item.put("registro", fotos.getString(fotos.getColumnIndex("id_registro")))
            item.put("foto", fotos.getString(fotos.getColumnIndex("ruta")))
            item.put("code",3)
            array.put(item)
        }
        return array
    }

    fun uploadFotos(context: Context,json:JSONArray?){
        Toast.makeText(context,"Subiendo evidencias...",Toast.LENGTH_SHORT).show()
        val url = "http://bimotest.com/api/monitoreo_fotos_api.php"
        if(json!=null){
            for (i in 0..json.length()-1){
                val row = json.getJSONObject(i)
                val jsonObject = JsonObjectRequest(Request.Method.POST,url,row,
                    {
                            response ->
                            Log.d("respuesta de fotos", response.toString())

                    },
                    {
                            error ->
                        Log.d("Error===>","Error al insertar trabajador: ${error.toString()}")
                    }
                )
                MySingleton.getInstance(context).addToRequestQueue(jsonObject)
            }
        }
    }
}