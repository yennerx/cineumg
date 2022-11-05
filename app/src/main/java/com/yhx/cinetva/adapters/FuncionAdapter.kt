package com.yhx.cinetva.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.yhx.cinetva.R
import com.yhx.cinetva.activities.SalaActivity
import com.yhx.cinetva.data.DataFuncion
import kotlinx.android.synthetic.main.item_funcion.view.*

class FuncionAdapter(private val dataFuncion: List<DataFuncion>, private val contexto: Context): RecyclerView.Adapter<FuncionAdapter.FuncionHolder>() {

    class FuncionHolder(private var vista: View, private var contexto: Context): RecyclerView.ViewHolder(vista){

        fun bind(dataFuncion: DataFuncion){
            vista.btFuncion.text = dataFuncion.hora
            vista.btFuncion.setOnClickListener{
                //Toast.makeText(vista.context,"ir a Sala: ${dataFuncion.sala}",Toast.LENGTH_SHORT).show()
                contexto.startActivity(Intent(contexto, SalaActivity::class.java).putExtra("pel",dataFuncion))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuncionHolder {
        return FuncionHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_funcion,
                parent,
                false
            ),contexto
        )
    }

    override fun onBindViewHolder(holder: FuncionHolder, position: Int) {
        holder.bind(dataFuncion[position])
    }

    override fun getItemCount(): Int {
        return dataFuncion.size
    }
}
