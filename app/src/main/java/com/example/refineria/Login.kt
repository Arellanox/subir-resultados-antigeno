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
import com.example.refineria.classes.PacientesAntigenoProvider
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
        //txtPassword.filters = arrayOf<InputFilter>(AllCaps())
        //txtUsuario.filters = arrayOf<InputFilter>(AllCaps())
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
        //syncUsers()
    }

    fun accessToDetail(){
        //Toast.makeText(this,"boton presionado de inicio",Toast.LENGTH_LONG).show()
        if(txtUsuario.text.toString().isNotEmpty() && txtPassword.text.toString().isNotEmpty()){
            val url = prefs.getLoginApi()
            //val url = "https://bimo-lab.com/movil/antigenos/api/login-api.php"
            val session = JSONObject()
            session.put("usuario",txtUsuario.text.toString())
            session.put("password", txtPassword.text.toString())

            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,session, {
                        response ->
                    //Toast.makeText(this,"respuesta: ${response.toString()}",Toast.LENGTH_LONG).show()
                    Log.d("respuesta",response.toString())
                    val json = response.getJSONObject("response");
                    val codigo = json.getInt("code")

                    if (codigo==1){
                        val datos = json.getJSONObject("datos")
                        Log.d("nombre",datos.getString("nombre"))
                        prefs.saveIdUsuario(datos.getString("id_usuario"))
                        prefs.saveIdCargoUsuario(datos.getString("id_cargo"))
                        prefs.saveNombreUsuario(datos.getString("nombre"))
                        prefs.saveProfesionUsuario(datos.getString("profesion"))
                        prefs.saveCedulaUsuario(datos.getString("cedula"))
                        prefs.saveTipoUsuario(datos.getString("tip_usuario"))
                        prefs.saveUsernameUsuario(datos.getString("username"))
                        prefs.savePuntoTrabajoUsuario(datos.getString("punto_trabajo_id"))
                        prefs.saveLugarTomaUsuario(datos.getString("lugar_de_toma"))
                        goToDetail()
                        Log.d("pref n ombre", prefs.getNombreUsuario())
                    }else{
                        Toast.makeText(this,"Error: Usuario y/o password incorrectos",Toast.LENGTH_LONG).show()
                    }

                },{
                        error ->
                    Toast.makeText(this,"Error: ${error.toString()}",Toast.LENGTH_LONG).show()
                    Log.d("error volley",error.toString())
                })
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }else{
            Toast.makeText(this, "Rellene los campos para logearse", Toast.LENGTH_LONG).show()
        }
    }

    fun goToDetail(){


        var usuario = prefs.getUsernameUsuario()

        startActivity(Intent(this, MainActivity::class.java))
        Toast.makeText(this, "Bienvenido $usuario", Toast.LENGTH_LONG).show()
    }

}