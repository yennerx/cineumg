package com.yhx.cinetva.data

import java.io.Serializable

data class DataFuncion(
    val no: Int,
    val hora: String,
    val fecha: String,
    val pelicula: Int,
    val sala: Int
) :Serializable
