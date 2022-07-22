package com.example.refineria.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.refineria.MainActivity
import com.example.refineria.R
import com.example.refineria.RecyclerAdapter
import com.example.refineria.classes.Fotos
import com.example.refineria.classes.PacientesAntigeno
import com.example.refineria.classes.PacientesAntigenoProvider
import kotlinx.android.synthetic.main.fragment_antigeno.*
import kotlinx.android.synthetic.main.fragment_antigeno.view.*

class Antigeno : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    val antigenos = PacientesAntigenoProvider()
    override fun onCreateView(
        inflater: LayoutInflater, container : ViewGroup?,
        saveInstanceState: Bundle?
    ): View?{
        return inflater.inflate(R.layout.fragment_antigeno, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        antigenos.obtenerListaPacientesAntigeno(requireContext())
        Toast.makeText(activity,"Cargado lista de pacientes...",Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            recycle()
        }, 1800)
    }

    fun recycle(){
        recyclerViewPacientesAntigeno.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = RecyclerAdapter(antigenos.lista)
        }
    }
}