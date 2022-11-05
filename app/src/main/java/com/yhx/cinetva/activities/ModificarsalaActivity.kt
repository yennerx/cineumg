package com.yhx.cinetva.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.yhx.cinetva.AppBarSecondary
import com.yhx.cinetva.R
import kotlinx.android.synthetic.main.activity_agregarsala.*
import kotlinx.android.synthetic.main.activity_agregarsala.etSalaNo
import kotlinx.android.synthetic.main.activity_modificarfuncion.*
import kotlinx.android.synthetic.main.activity_modificarsala.*

class ModificarsalaActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificarsala)

        var titulo = "Modificar Sala"
        AppBarSecondary().show(this,titulo,true )

        btSalaBuscar.setOnClickListener {
            salaBuscar()
        }

        btSalaModificar.setOnClickListener {
            salaModificar()
        }

        btSalaEliminar.setOnClickListener {
            salaEliminar()
        }
    }

    fun salaBuscar(){
        val salaNo: String = etSalaNo2.text.toString()

        db.collection("salas").document(salaNo).get().addOnSuccessListener {
            etSalaCapacidad2.setText(it.get("capacidad") as String?)
            etSalaCategoria2.setText(it.get("categoria") as String?)
        }
    }


    fun salaModificar(){
        val salaNo: String = etSalaNo2.text.toString()
        val salaCapacidad:String = etSalaCapacidad2.text.toString()
        val salaCategoria:String = etSalaCategoria2.text.toString()
        val salaOcupados = ""
        val salaPelicula = ""

        if(salaNo != ""){
            db.collection("salas").document(salaNo).set(
                hashMapOf("capacidad" to salaCapacidad,
                    "categoria" to salaCategoria,
                    "ocupados" to salaOcupados,
                    "pelicula" to salaPelicula
                )
            )

            etSalaNo2.setText("")
            etSalaCapacidad2.setText("")
            etSalaCategoria2.setText("")
        }else{
            Toast.makeText(this,"Por favor ingresar un No. de Sala", Toast.LENGTH_SHORT).show()
        }
    }

    fun salaEliminar(){
        val salaNo: String = etSalaNo2.text.toString()
        db.collection("salas").document(salaNo).delete()

        etSalaNo2.setText("")
        etSalaCapacidad2.setText("")
        etSalaCategoria2.setText("")
    }
}