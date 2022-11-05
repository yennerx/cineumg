package com.yhx.cinetva.activities

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yhx.cinetva.AppBarSecondary
import com.yhx.cinetva.R
import kotlinx.android.synthetic.main.activity_agregarpeli.*
import kotlinx.android.synthetic.main.activity_modificarpeli.*

class AgregarpeliActivity : AppCompatActivity() {

    //private val db = FirebaseFirestore.getInstance()
    private val db = Firebase.firestore
    //val clasifPeliList = arrayOf("A", "B", "B12", "B15", "C")
    //val spinner = findViewById<Spinner>(R.id.etFuncionIdPeli)
    //val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, clasifPeliList)
    //lateinit var option : Spinner
    //lateinit var result : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregarpeli)

        var titulo = "Agregar Película"
        AppBarSecondary().show(this,titulo,true )

        btPeliAgregar.setOnClickListener {
            peliAgregar()
        }

    }

    fun peliAgregar(){
        val peliId: String = etPeliId.text.toString()
        val peliNombre:String = etPeliNombre.text.toString()
        val peliSinopsis:String = etPeliSinopsis.text.toString()
        val peliClasificacion:String = etPeliClasificacion.text.toString()
        val peliUrlImagen:String = etPeliPoster.text.toString()

        if(peliId != ""){
            val pelicula = hashMapOf(
                "clasificacion" to peliClasificacion,
                "id" to peliId,
                "nombre" to peliNombre,
                "sinopsis" to peliSinopsis,
                "urlimagen" to peliUrlImagen)

            //Agregar peliculas con ID manual
            db.collection("peliculas").document(pelicula.getValue("id")).set(pelicula)

            etPeliId.setText("")
            etPeliNombre.setText("")
            etPeliSinopsis.setText("")
            etPeliClasificacion.setText("")
            etPeliPoster.setText("")
        }else{
            Toast.makeText(this,"Por favor ingresar un ID",Toast.LENGTH_SHORT).show()
        }

    }
}
