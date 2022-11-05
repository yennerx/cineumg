package com.yhx.cinetva.data

import java.io.Serializable

data class DataPelicula(
    val id: Int,
    val titulo: String,
    val sinopsis: String,
    val clasificacion: String,
    val poster: String
) : Serializable
