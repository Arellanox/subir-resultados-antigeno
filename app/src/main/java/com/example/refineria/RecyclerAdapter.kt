package com.example.refineria

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.refineria.classes.PacientesAntigeno

class RecyclerAdapter(val pacientesAntigenoList:List<PacientesAntigeno>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val nombre = arrayOf("d116df5",
        "36ffc75", "f5cfe78", "5b87628",
        "db8d14e", "9913dc4", "e120f96",
        "466251b")

    private val prefolio = arrayOf("Kekayaan", "Teknologi",
        "Keluarga", "Bisnis",
        "Keluarga", "Hutang",
        "Teknologi", "Pidana")

    private val procedencia = arrayOf("pertanyaan 9",
        "pertanyaan 11", "pertanyaan 17", "test forum",
        "pertanyaan 12", "pertanyaan 18", "pertanyaan 20",
        "pertanyaan 21")

    private val segmentos = arrayOf("pertanyaan 9",
        "pertanyaan 11", "pertanyaan 17", "test forum",
        "pertanyaan 12", "pertanyaan 18", "pertanyaan 20",
        "pertanyaan 21")

    private val fecharegistro = arrayOf("pertanyaan 9",
        "pertanyaan 11", "pertanyaan 17", "test forum",
        "pertanyaan 12", "pertanyaan 18", "pertanyaan 20",
        "pertanyaan 21")

    private val resultado = arrayOf("Positivo",
        "Negativo", "Positivo", "Agregar",
        "Negativo", "Negativo", "Positivo",
        "Agregar")

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

            itemView.setOnClickListener {
                var position: Int = getAdapterPosition()
                val context = itemView.context
                val intent = Intent(context, procesar_paciente::class.java).apply {
                    putExtra("NUMERO", position)
                    putExtra("NOMBRE", itemNombre.text)
                    putExtra("PREFOLIO", itemPrefolio.text)
                    putExtra("RESULTADO", itemResultado.text)
                }
                context.startActivity(intent)
            }
        }

        fun render(paciente : PacientesAntigeno){
            itemNombre.text = paciente.nombre
            itemPrefolio.text = paciente.prefolio
            itemProcedencia.text = paciente.procedencia
            itemSegmento.text = paciente.segmento
            itemFechaRegistro.text = paciente.fechaIngreso
            itemResultado.text = paciente.resultado
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
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
        viewHolder.render(item)

    }

    override fun getItemCount(): Int {
        return pacientesAntigenoList.size
    }

}

