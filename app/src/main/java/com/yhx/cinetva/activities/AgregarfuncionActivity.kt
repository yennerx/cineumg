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

class AgregarfuncionActivity : AppCompatActivity() {

    //private val db = FirebaseFirestore.getInstance()
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregarfuncion)

        var titulo = "Agregar Funcion"
        AppBarSecondary().show(this,titulo,true )

        btFuncionAgregar.setOnClickListener {
            funcionAgregar()
        }
    }

    fun funcionAgregar(){
        val funcionNo: String = etFuncionNo.text.toString()
        val funcionHora:String = etFuncionHora.text.toString()
        val funcionFecha:String = etFuncionFecha.text.toString()
        val funcionIdPeli:String = etFuncionIdPeli.text.toString()
        val funcionNosala:String = etFuncionNoSala.text.toString()

        if(funcionNo != ""){
            val funcion = hashMapOf(
                "hora" to funcionHora,
                "fecha" to funcionFecha,
                "idpeli" to funcionIdPeli,
                "nosala" to funcionNosala)

            db.collection("funciones").document(funcion.getValue("idpeli")).set(funcion)

            etFuncionNo.setText("")
            etFuncionHora.setText("")
            etFuncionFecha.setText("")
            etFuncionIdPeli.setText("")
            etFuncionNoSala.setText("")
        }else{
            Toast.makeText(this,"Por favor ingresar un No. de Funcion", Toast.LENGTH_SHORT).show()
        }
    }
}