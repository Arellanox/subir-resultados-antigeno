package com.example.refineria.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
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
import com.example.refineria.network.MySingleton
import com.example.refineria.procesar_paciente
import com.example.refineria.sharedpreference.RefineriaApplication.Companion.prefs
import kotlinx.android.synthetic.main.fragment_antigeno.*
import kotlinx.android.synthetic.main.fragment_antigeno.view.*
import org.json.JSONObject
import java.lang.ClassCastException
import kotlin.collections.contains as contains1

class Antigeno : Fragment(), MainActivity.refreshList {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    var antigenos = PacientesAntigenoProvider()
    var itemSearchView:SearchView?=null
    var listaActual:List<PacientesAntigeno>?=null


    override fun onCreateView(
        inflater: LayoutInflater, container : ViewGroup?,
        saveInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.fragment_antigeno, container, false)
        antigenos.obtenerListaPacientesAntigeno(requireContext())

        view.busqueda.setOnQueryTextFocusChangeListener{v, hasFocus ->
            Log.d("LISTENERFOCUS",hasFocus.toString())
        }

        view.busqueda.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(submit: String?): Boolean {
                val url = prefs.filtrarLista()
                val parametro = JSONObject()
                parametro.put("parametro",submit)

                val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,parametro,
                    {
                            response ->
                        //Toast.makeText(this,"respuesta: ${response.toString()}",Toast.LENGTH_LONG).show()
                        Log.d("respuesta",response.toString())


                    },{
                            error ->

                        Log.d("error volley",error.toString())
                    })
                MySingleton.getInstance(requireContext()).addToRequestQueue(jsonObjectRequest)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("Textoingresado",p0.toString())
                return true
            }

        })

        return view
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        Toast.makeText(activity,"Cargando lista de pacientes...",Toast.LENGTH_SHORT).show()
        try{
            Handler(Looper.getMainLooper()).postDelayed({
                /* Create an Intent that will start the Menu-Activity. */
                recycle(antigenos.lista)
            }, 1500)
        }catch (e: Exception){

        }
        /*var estado = antigenos.recuperarEstadoLista()
        while (estado==0){
            //esperar a que se recupere la informacion
            Log.d("esperando los valores",estado.toString())
            estado = antigenos.recuperarEstadoLista()
        }
        recycle(antigenos.lista)*/
    }

    fun recycle(lista:List<PacientesAntigeno>){
        listaActual = lista
        try{
            recyclerViewPacientesAntigeno.apply {
                // set a LinearLayoutManager to handle Android
                // RecyclerView behavior
                layoutManager = LinearLayoutManager(activity)
                // set the custom adapter to the RecyclerView
                adapter = RecyclerAdapter(lista) { paciente -> onItemSelected(paciente) }
            }
        }catch (e: Exception){

        }

    }

    fun onItemSelected(paciente:PacientesAntigeno){
        val intent = Intent(activity, procesar_paciente::class.java).apply {
            putExtra("id_paciente", paciente.id_paciente)
            putExtra("folio", paciente.folio)
            putExtra("folioOrden", paciente.folioOrden)
            putExtra("nombre", paciente.nombre)
            putExtra("resultado", paciente.resultado)
            putExtra("fechaResultado", paciente.fechaResultado)
            putExtra("prefolio", paciente.prefolio)
            putExtra("segmento", paciente.segmento)
            putExtra("sexo", paciente.sexo)
            putExtra("fechaIngreso", paciente.fechaIngreso)
            putExtra("indicador", paciente.indicador)
            putExtra("origen", paciente.origen)
            putExtra("lugarExtra", paciente.lugarExtra)
            putExtra("procedencia", paciente.procedencia)
            putExtra("edad", paciente.edad)
        }
        startActivity(intent)
    }

    override fun refresh(list: List<PacientesAntigeno>) {
        super.refresh(list)
        recycle(list)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}