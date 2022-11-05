package com.yhx.cinetva.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yhx.cinetva.AppBarSecondary
import com.yhx.cinetva.R
import kotlinx.android.synthetic.main.activity_agregarpeli.*
import kotlinx.android.synthetic.main.activity_modificarpeli.*

class ModificarpeliActivity : AppCompatActivity() {

    //private val db = FirebaseFirestore.getInstance()
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificarpeli)

        var titulo = "Modificar Película"
        AppBarSecondary().show(this,titulo,true )

        btPeliModificar2.setOnClickListener {
            peliModificar()
        }

        btPeliBuscar2.setOnClickListener {
            peliBuscar()
        }

        btPeliEliminar2.setOnClickListener {
            peliEliminar()
        }
    }

    fun peliBuscar(){
        val peliId: String = etPeliId2.text.toString()

        db.collection("peliculas").document(peliId).get().addOnSuccessListener {
            etPeliNombre2.setText(it.get("nombre") as String?)
            etPeliSinopsis2.setText(it.get("sinopsis") as String?)
            etPeliClasificacion2.setText(it.get("clasificacion") as String?)
            etPeliPoster2.setText(it.get("urlimagen") as String?)
        }
    }

    fun peliModificar(){
        val peliId: String = etPeliId2.text.toString()
        val peliNombre:String = etPeliNombre2.text.toString()
        val peliSinopsis:String = etPeliSinopsis2.text.toString()
        val peliClasificacion:String = etPeliClasificacion2.text.toString()
        val peliUrlImagen:String = etPeliPoster2.text.toString()

        if(peliId != ""){
            db.collection("peliculas").document(peliId).set(
                hashMapOf("nombre" to peliNombre,
                    "sinopsis" to peliSinopsis,
                    "clasificacion" to peliClasificacion,
                    "urlimagen" to peliUrlImagen)
            )

            etPeliId2.setText("")
            etPeliNombre2.setText("")
            etPeliSinopsis2.setText("")
            etPeliClasificacion2.setText("")
            etPeliPoster2.setText("")
        }else{
            Toast.makeText(this,"Por favor ingresar un ID", Toast.LENGTH_SHORT).show()
        }
    }

    fun peliEliminar(){
        val peliId: String = etPeliId2.text.toString()
        db.collection("peliculas").document(peliId).delete()

        etPeliId2.setText("")
        etPeliNombre2.setText("")
        etPeliSinopsis2.setText("")
        etPeliClasificacion2.setText("")
        etPeliPoster2.setText("")
    }
}