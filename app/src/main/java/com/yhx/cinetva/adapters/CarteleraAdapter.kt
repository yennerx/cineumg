package com.yhx.cinetva.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yhx.cinetva.activities.PeliculaActivity
import com.yhx.cinetva.R
import com.yhx.cinetva.data.DataPelicula
import kotlinx.android.synthetic.main.item_cartelera.view.*

class CarteleraAdapter (private val dataPelicula:List<DataPelicula>, private var contexto: Context):RecyclerView.Adapter<CarteleraAdapter.CarteleraHolder>(){

    class CarteleraHolder(private var vista: View, private var contexto: Context):RecyclerView.ViewHolder(vista){

        fun bind(dataPelicula: DataPelicula){
            vista.tvTitulo.text = dataPelicula.titulo
            vista.tvSinopsis.text = dataPelicula.sinopsis
            vista.tvClasificacion.text = "Clasificación: ${dataPelicula.clasificacion}"
            Picasso.get().load(dataPelicula.poster).into(vista.ivPoster)

            vista.setOnClickListener{
                //Toast.makeText(vista.context,"Proceder a película: ${dataPelicula.titulo}",Toast.LENGTH_SHORT).show()
                contexto.startActivity(Intent(contexto, PeliculaActivity::class.java).putExtra("pel",dataPelicula))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarteleraHolder {
        return  CarteleraHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cartelera,
                parent,
                false
            ), contexto
        )
    }

    override fun onBindViewHolder(holder: CarteleraHolder, position: Int) {
        holder.bind(dataPelicula[position])
    }

    override fun getItemCount(): Int {
        return dataPelicula.size
    }
}