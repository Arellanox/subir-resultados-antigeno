package com.example.refineria

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.AllCaps
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.refineria.classes.Pafre
import com.example.refineria.classes.Usuarios
import com.example.refineria.database.SQLite
import com.example.refineria.network.MySingleton
import com.example.refineria.sharedpreference.RefineriaApplication.Companion.prefs
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkUserValues()
        initUI()
        txtPassword.filters = arrayOf<InputFilter>(AllCaps())
        txtUsuario.filters = arrayOf<InputFilter>(AllCaps())
    }

    fun checkUserValues(){
        if(prefs.getName().isNotEmpty()){
            goToDetail()
        }
    }

    fun initUI(){
        btnIniciarSesion.setOnClickListener{
            accessToDetail()
        }
        syncUsers()
    }

    fun accessToDetail(){
        //Toast.makeText(this,"boton presionado de inicio",Toast.LENGTH_LONG).show()
        if(txtUsuario.text.toString().isNotEmpty() && txtPassword.text.toString().isNotEmpty()){
            val url = "http://bimotest.com/api/monitoreo_usuario_session.php"
            val session = JSONObject()
            session.put("usuario",txtUsuario.text.toString())
            session.put("password", txtPassword.text.toString())
            session.put("version","movil")

            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,session,
                {
                        response ->
                    //Toast.makeText(this,"respuesta: ${response.toString()}",Toast.LENGTH_LONG).show()
                    Log.d("respuesta",response.toString())


                    val con= SQLite(this, "Refineria", null, 1)
                    val database= con.writableDatabase


                    val sqlFrentes2: String = "SELECT id_frente FROM monitoreo_paramedico_frentes WHERE paramedico = '${response.getString("id")}'"
                    val rowFrentes = database.rawQuery(sqlFrentes2, null)

                    if(rowFrentes.moveToNext()){
                        prefs.saveName(txtUsuario.text.toString())
                        prefs.savePassword(txtPassword.text.toString())
                        prefs.saveId(response.getString("id"))
                        goToDetail()
                    }else{
                        Toast.makeText(this,"A este usuario le falta configurar sus frentes",Toast.LENGTH_LONG).show()
                    }
                },{
                        error ->
                    Toast.makeText(this,"Error: Usuario y/o password incorrectos",Toast.LENGTH_LONG).show()
                    Log.d("error volley",error.toString())
                })
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }else{
            Toast.makeText(this, "Rellene los campos para logearse", Toast.LENGTH_LONG).show()
        }
    }

    fun goToDetail(){
        var usuario = prefs.getName()
        startActivity(Intent(this, MainActivity::class.java))
        Toast.makeText(this, "Bienvenido $usuario", Toast.LENGTH_LONG).show()
    }

    fun syncUsers(){
        val user = Usuarios()
        user.downloadUsers(this)
        val pafre = Pafre()
        pafre.downloadPafres(this)
        /*Toast.makeText(this,"Iniciando sincronizacion de usuarios", Toast.LENGTH_LONG).show()
        val queue = Volley.newRequestQueue(this)
        val url = "https://android-kotlin-fun-mars-server.appspot.com/photos"
        val method = Request.Method.GET

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonArrayRequest(method, url, null,
            { response ->

                Toast.makeText(this,"response ${response.toString()/*response.getJSONObject(0).getString("id")*/}",Toast.LENGTH_LONG).show()
                /**/
            },
            { error ->
                // TODO: Handle error
                Toast.makeText(this,"Error. ${error.toString()}",Toast.LENGTH_LONG).show()
            }
        )
        // Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)*/
    }
}