package com.yhx.cinetva.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.yhx.cinetva.AppBarSecondary
import com.yhx.cinetva.R
import kotlinx.android.synthetic.main.activity_modificarfuncion.*
import kotlinx.android.synthetic.main.activity_modificarpeli.*

class ModificarfuncionActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificarfuncion)

        var titulo = "Modificar Función"
        AppBarSecondary().show(this,titulo,true )

        btFuncionModificar.setOnClickListener {
            funcionModificar()
        }

        btFuncionBuscar.setOnClickListener {
            funcionBuscar()
        }

        btFuncionEliminar.setOnClickListener {
            funcionEliminar()
        }


    }

    fun funcionBuscar(){
        val funcionNo: String = etFuncionNo2.text.toString()

        db.collection("funciones").document(funcionNo).get().addOnSuccessListener {
            etFuncionHora2.setText(it.get("hora") as String?)
            etFuncionFecha2.setText(it.get("fecha") as String?)
            etFuncionIdPeli2.setText(it.get("idpeli") as String?)
            etFuncionNoSala2.setText(it.get("nosala") as String?)
        }
    }

    fun funcionModificar(){
        val funcionNo: String = etFuncionNo2.text.toString()
        val funcionHora:String = etFuncionHora2.text.toString()
        val funcionFecha:String = etFuncionFecha2.text.toString()
        val funcionIdPeli:String = etFuncionIdPeli2.text.toString()
        val funcionNosala:String = etFuncionNoSala2.text.toString()

        if(funcionNo != ""){
            db.collection("funciones").document(funcionNo).set(
                hashMapOf("hora" to funcionHora,
                    "fecha" to funcionFecha,
                    "idpeli" to funcionIdPeli,
                    "nosala" to funcionNosala)
            )

            etFuncionNo2.setText("")
            etFuncionHora2.setText("")
            etFuncionFecha2.setText("")
            etFuncionIdPeli2.setText("")
            etFuncionNoSala2.setText("")
        }else{
            Toast.makeText(this,"Por favor ingresar un No. de Funcion", Toast.LENGTH_SHORT).show()
        }
    }

    fun funcionEliminar(){
        val funcionNo: String = etFuncionNo2.text.toString()
        db.collection("funciones").document(funcionNo).delete()

        etFuncionNo2.setText("")
        etFuncionHora2.setText("")
        etFuncionFecha2.setText("")
        etFuncionIdPeli2.setText("")
        etFuncionNoSala2.setText("")
    }
}