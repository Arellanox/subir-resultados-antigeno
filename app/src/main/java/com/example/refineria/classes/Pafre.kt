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

class Pafre {

    fun downloadPafres(context:Context){
        this.dropTable(context)
        this.createTable(context)
        Toast.makeText(context,"Descargando relacion frentes-paramédicos",Toast.LENGTH_SHORT).show()
        val url = "http://bimotest.com/api/monitoreo_pafre_get_movil.php"
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,url,null,
            {
                    response ->
                try {
                    insertPafre(context,response)

                }catch (e: Exception){

                    Log.d("ERROR EN FOTOS",e.toString())
                }
            },
            {
                    error ->
                Toast.makeText(context,"Ha ocurrido un error ${error}", Toast.LENGTH_LONG).show()
            }
        )
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }

    fun insertPafre(context: Context,json: JSONArray?){
        Toast.makeText(context,"Descargando usuarios",Toast.LENGTH_LONG).show()
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        if (json!=null){
            for (i in 0..json.length()-1){
                var row = json.getJSONObject(i)
                var insert = ContentValues().apply {
                    put("id_pa_fre",row.getInt("ID_PA_FRE"))
                    put("paramedico",row.getInt("PARAMEDICO"))
                    put("id_frente",row.getInt("ID_FRENTE"))
                }
                database.insert("monitoreo_paramedico_frentes",null,insert)

            }
        }
    }

    fun dropTable(context: Context){
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        database.execSQL("DROP TABLE IF EXISTS monitoreo_paramedico_frentes")
    }

    fun createTable(context: Context){
        var con= SQLite(context, "Refineria", null, 1)
        var database= con.writableDatabase
        database.execSQL("CREATE TABLE monitoreo_paramedico_frentes(" +
                "id_pa_fre int primary key," +
                "paramedico int," +
                "id_frente int);")
    }
}