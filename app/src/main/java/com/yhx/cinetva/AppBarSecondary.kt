package com.yhx.cinetva

import androidx.appcompat.app.AppCompatActivity

class AppBarSecondary {
    fun show(actividad: AppCompatActivity, titulo: String, botonArriba: Boolean){
        actividad.setSupportActionBar(actividad.findViewById(R.id.toolbar_secondary))
        actividad.supportActionBar?.title = titulo
        actividad.supportActionBar?.setDisplayHomeAsUpEnabled(botonArriba)
    }
}