package com.example.refineria.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.refineria.Login
import com.example.refineria.R
import com.example.refineria.classes.*
import com.example.refineria.database.SQLite
import com.example.refineria.sharedpreference.RefineriaApplication.Companion.prefs
import kotlinx.android.synthetic.main.fragment_perfil.view.*


class PerfilFragment : Fragment() {

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container : ViewGroup?,
        saveInstanceState: Bundle?
    ): View?{

        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        view.txtUsuario.setText(prefs.getUsernameUsuario())
        view.txtNombre.setText(prefs.getNombreUsuario())
        view.txtProfesion.setText(prefs.getProfesionUsuario())
        //view.txtSexo.setText(prefs.getSexo())
        //view.txtNss.setText(prefs.getNss())

        view.btnSesion.setOnClickListener{
            prefs.wipe()
            Toast.makeText(context, "¡Adios!", Toast.LENGTH_SHORT).show()
            val editProfileIntent = Intent(getActivity(), Login::class.java)
            getActivity()?.startActivity(editProfileIntent)
        }

        /* view.sync_data_btn.setOnClickListener {
            val c = context
            //Toast.makeText(context,"Descargando datos",Toast.LENGTH_LONG).show()
            val frentes = Frentes()
            val trabajadores = Trabajadores()
            val registros = Registros()
            val fotos = Fotos()
            val pafre = Pafre()
            val users = Usuarios()
            if (c != null) {
                users.downloadUsers(c)
                frentes.downloadFrentes(c)
                val loteTrabajadores = trabajadores.getTrabajadores(c)
                trabajadores.uploadTrabajadores(c,loteTrabajadores)
                //trabajadores.downloadTrabajadores(c)
                val loteRegistros = registros.getRegistros(c)
                registros.uploadRegistros(c,loteRegistros)
                val loteFotos = fotos.getFotos(c)
                fotos.uploadFotos(c,loteFotos)
                pafre.downloadPafres(c)
            }
        } */

        return view
    }


}