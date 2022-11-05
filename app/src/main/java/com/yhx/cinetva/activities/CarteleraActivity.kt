package com.yhx.cinetva.activities

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yhx.cinetva.AppBarSecondary
import com.yhx.cinetva.R
import com.yhx.cinetva.adapters.CarteleraAdapter
import com.yhx.cinetva.data.DataPelicula
import kotlinx.android.synthetic.main.activity_cartelera.*

class CarteleraActivity : AppCompatActivity() {

    //private val db = FirebaseFirestore.getInstance()
    //val peliculas = db.collection("peliculas")
    val db = Firebase.firestore
    var name: String? = null
    var lista = ArrayList<DataPelicula>()
    val movie = mutableMapOf<Any, Any>()
    val cinta = mutableListOf<String>()

    //val movie = mutableListOf<String>()



    private fun peliculasPrueba():ArrayList<DataPelicula>{

        var prueba = "NA"
        //var movie = "hola"
        //var pelicula = hashMapOf<String, String, String, String, String>()

        db.collection("peliculas")
            .get()
            .addOnSuccessListener { result ->
               for(document in result){
                   //System.out.println("${document.id} => ${document.data.get("nombre")} -------${result}")
                   //lista.add(DataPelicula(document.id.toInt(),document.data.get("nombre").toString(),document.data.get("sinopsis").toString(),document.data.get("clasificacion").toString(),document.data.get("urlimagen").toString()))
                   lista.add(
                       DataPelicula(
                           document.data.getValue("id").toString().toInt(),
                           document.data.getValue("nombre").toString(),
                           document.data.getValue("sinopsis").toString(),
                           document.data.getValue("clasificacion").toString(),
                           document.data.getValue("urlimagen").toString()

                       )
                   )
                   name = document.data.getValue("nombre").toString()


                   movie.put("id", document.data.getValue("id").toString().toInt())
                   movie.put("nombre", document.data.getValue("nombre").toString())
                   movie.put("sinopsis", document.data.getValue("sinopsis").toString())
                   movie.put("clasificacion", document.data.getValue("clasificacion").toString())
                   movie.put("urlimagen", document.data.getValue("urlimagen").toString())

                   //cinta.add(name)

                   System.out.println(document.id)
                   System.out.println(document.data.get("nombre").toString())
                   System.out.println(document.data.get("sinopsis").toString())
                   System.out.println(document.data.get("clasificacion").toString())
                   System.out.println(document.data.get("urlimagen").toString())

               }
               //Toast.makeText(this,"Cantidad de Peliculas: ${result.size()} -prueba:${prueba}",Toast.LENGTH_SHORT).show()


           }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                Toast.makeText(this, "Error al leer",Toast.LENGTH_SHORT).show()
            }

        Toast.makeText(this,"Movie name: ${movie}",Toast.LENGTH_SHORT).show()

        Toast.makeText(this,"Película agregada: ${prueba}",Toast.LENGTH_SHORT).show()

        //if (lista.isEmpty()){
        if (name != null){
            Toast.makeText(this,"No se fue posible cargar peliculas de firebase",Toast.LENGTH_SHORT).show()
            lista.add(DataPelicula(6,"Raya y el último dragón","El nuevo clásico animado de Disney, el primero desde 'Frozen 2'. Dirigida por Paul Briggs y Dean Wellins", "A", "https://i.blogs.es/28ca41/raya/1366_2000.jpeg"))
            lista.add(DataPelicula(7,"The Many Saints of Newark","Precuela de 'Los Soprano', una de las series de televisión más aclamadas de todos los tiempos.", "C", "https://i.blogs.es/a8ffff/newark/1366_2000.jpeg"))
            lista.add(DataPelicula(8,"Bob's Burgers: The Movie","Salto a la gran pantalla de la querida serie animada que ya va por su undécima temporada.", "C", "https://i.blogs.es/21b473/burgers/1366_2000.jpeg"))
            lista.add(DataPelicula(9,"Last Night in Soho","La nueva película de Edgar Wright es un thriller sobre una mujer que conoce a su ídolo en los años 60 en Londres, a donde llega de forma misteriosa.", "C", "https://i.blogs.es/f9c0f2/soho/1366_2000.jpeg"))
            lista.add(DataPelicula(10,"Godzilla vs. Kong","Adam Wingard dirige el enfrentamiento entre dos de los monstruos cinematográficos más míticos de todos los tiempos. ", "A", "https://i.blogs.es/9228a8/godzilla-kong/1366_2000.jpeg"))
        }

        return lista
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartelera)

        rvCartelera.layoutManager = LinearLayoutManager(this)
        rvCartelera.adapter = CarteleraAdapter(peliculasPrueba(), this)

        var titulo = "Cartelera para hoy"
        AppBarSecondary().show(this,titulo,true )
    }
}