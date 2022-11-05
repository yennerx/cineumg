package com.yhx.cinetva.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yhx.cinetva.AppBarSecondary
import com.yhx.cinetva.R
import kotlinx.android.synthetic.main.activity_agregarfuncion.*
import kotlinx.android.synthetic.main.activity_agregarfuncion.etFuncionNo
import kotlinx.android.synthetic.main.activity_agregarsala.*

class AgregarsalaActivity : AppCompatActivity() {

    //private val db = FirebaseFirestore.getInstance()
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregarsala)

        var titulo = "Agregar Sala"
        AppBarSecondary().show(this,titulo,true )

        btSalaAgregar.setOnClickListener {
            salaAgregar()
        }
    }

    fun salaAgregar(){
        val salaNo: String = etSalaNo.text.toString()
        val salaCapacidad:String = etSalaCapacidad.text.toString()
        val salaCategoria:String = etSala
        val salaOcupados = 0
        val salaPelicula = 0


        if(salaNo != ""){
            db.collection("salas").document(salaNo).set(
                hashMapOf("capacidad" to salaCapacidad,
                    "categoria" to salaCategoria,
                    "ocupados" to salaOcupados,
                    "pelicula" to salaPelicula)
            )

            etSalaNo.setText("")
            etSalaCapacidad.setText("")
            etSalaCategoria.setText("")
        }else{
            Toast.makeText(this,"Por favor ingresar un No. de Sala", Toast.LENGTH_SHORT).show()
        }
    }
}