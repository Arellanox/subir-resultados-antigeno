package com.example.refineria.fragments

import android.content.Context
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
import java.lang.ClassCastException

class Antigeno : Fragment(), MainActivity.refreshList {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    var antigenos = PacientesAntigenoProvider()

    override fun onCreateView(
        inflater: LayoutInflater, container : ViewGroup?,
        saveInstanceState: Bundle?
    ): View?{
        antigenos.obtenerListaPacientesAntigeno(requireContext())
        return inflater.inflate(R.layout.fragment_antigeno, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        Toast.makeText(activity,"Cargando lista de pacientes...",Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            recycle(antigenos.lista)
        }, 1500)

    }

    fun recycle(lista:List<PacientesAntigeno>){
        recyclerViewPacientesAntigeno.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = RecyclerAdapter(lista)
        }
    }

    override fun refresh(list: List<PacientesAntigeno>) {
        super.refresh(list)
        recycle(list)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}