package com.yhx.cinetva.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.yhx.cinetva.AppBarSecondary
import com.yhx.cinetva.MainActivity
import com.yhx.cinetva.R
import com.yhx.cinetva.adapters.SalaAdapter
import com.yhx.cinetva.data.DataFuncion
import com.yhx.cinetva.data.DataSala
import kotlinx.android.synthetic.main.activity_sala.*

class SalaActivity : AppCompatActivity() {

    private fun salasPrueba(): ArrayList<DataSala>{
        val lista = ArrayList<DataSala>()
        lista.add(DataSala(1,20,8,"Estandar",1))
        lista.add(DataSala(2,16,4,"VIP",1))
        lista.add(DataSala(3,28,20,"3D",1))
        return lista
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sala)

        val funcion = intent.getSerializableExtra("pel") as DataFuncion

        tvNoSala.setText("Sala No. ${funcion.sala}")
        tvCategoria.setText("Categoria: ${salasPrueba().get(funcion.sala-1).categoria}")
        tvCapacidad.setText("Capacidad: ${salasPrueba().get(funcion.sala -1).capacidad}")
        tvOcupados.setText("Ocupados: ${salasPrueba().get(funcion.sala-1).ocupados}")
        tvDisponibles.setText("Disponibles: ${salasPrueba().get(funcion.sala-1).capacidad - salasPrueba().get(funcion.sala-1).ocupados}")

        rvSala.layoutManager = GridLayoutManager(this,4)
        rvSala.adapter = SalaAdapter(salasPrueba(), this, salasPrueba().get(funcion.sala -1).capacidad)

        btFinalizar.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        var titulo = "Seleccione Asientos"
        AppBarSecondary().show(this,titulo,false )
    }
}