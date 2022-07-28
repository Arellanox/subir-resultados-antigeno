package com.example.refineria

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.refineria.classes.PacientesAntigeno
import java.util.ArrayList

class RecyclerAdapter(var pacientesAntigenoList:List<PacientesAntigeno>, val onClickListener:(PacientesAntigeno) -> Unit): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemNombre: TextView
        var itemPrefolio: TextView
        var itemProcedencia: TextView
        var itemSegmento: TextView
        var itemFechaRegistro: TextView
        val avatar = itemView.findViewById(R.id.ImagenPaciente) as ImageView
        var itemResultado: TextView

        init {
            itemNombre = itemView.findViewById(R.id.txtNombre)
            itemPrefolio = itemView.findViewById(R.id.txtPrefolio)
            itemProcedencia = itemView.findViewById(R.id.txtProcedencia)
            itemSegmento = itemView.findViewById(R.id.txtSegmento)
            itemFechaRegistro = itemView.findViewById(R.id.txTFechaRegistro)
            itemResultado = itemView.findViewById(R.id.txtResultado)


        }

        fun render(paciente : PacientesAntigeno,onClickListener:(PacientesAntigeno) -> Unit){
            itemNombre.text = paciente.nombre
            itemPrefolio.text = paciente.prefolio
            itemProcedencia.text = paciente.procedencia
            itemSegmento.text = paciente.segmento
            itemFechaRegistro.text = paciente.fechaIngreso
            itemResultado.text = paciente.resultado

            itemView.setOnClickListener {
                /*var position: Int = getAdapterPosition()
                var paciente = pacientesAntigenoList[position]
                val context = itemView.context
                val intent = Intent(context, procesar_paciente::class.java).apply {
                    putExtra("NUMERO", position)
                    putExtra("NOMBRE", itemNombre.text)
                    putExtra("PREFOLIO", itemPrefolio.text)
                    putExtra("RESULTADO", itemResultado.text)
                    putExtra("paciente",paciente.toString())
                }
                context.startActivity(intent)*/
                onClickListener(paciente)
            }
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        //anti.obtenerListaPacientesAntigeno(viewGroup.context)
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.trabajadores_cardview, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        /*viewHolder.itemNombre.text = nombre[i]
        viewHolder.itemPrefolio.text = prefolio[i]
        viewHolder.itemProcedencia.text = procedencia[i]
        viewHolder.itemSegmento.text = segmentos[i]
        viewHolder.itemFechaRegistro.text = fecharegistro[i]
        if (resultado[i] == "Positivo"){
            viewHolder.avatar.setImageResource(R.drawable.positive);
        }else if (resultado[i] == "Negativo"){
            viewHolder.avatar.setImageResource(R.drawable.negative);
        }else{
            viewHolder.avatar.setImageResource(R.drawable.plus);
        }
        viewHolder.itemResultado.text = resultado[i]*/
        val item = pacientesAntigenoList[i]

        if(item.resultado=="Positivo"){
            viewHolder.avatar.setImageResource(R.drawable.positive)
            viewHolder.itemResultado.setTextColor(Color.RED)
        }else if(item.resultado=="Negativo"){
            viewHolder.avatar.setImageResource(R.drawable.negative)
            viewHolder.itemResultado.setTextColor(Color.GREEN)
        }else{
            viewHolder.avatar.setImageResource(R.drawable.plus);
            viewHolder.itemResultado.setTextColor(Color.BLACK)
        }
        viewHolder.render(item, onClickListener)

    }

    override fun getItemCount(): Int {
       return pacientesAntigenoList.size
    }

}

