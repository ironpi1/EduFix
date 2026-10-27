package com.example.eduffixx.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Crea una tabla llamada "tickets".
@Entity(tableName = "tickets")
data class Ticket(

    // Identificador único autoincremental.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Título del problema (ej. "Cable expuesto").
    val titulo: String,

    // Lugar del colegio (ej. "Sala 4B", "Patio").
    val ubicacion: String,

    // Descripción del problema.
    val descripcion: String,

    // Estado del reporte ("Pendiente" o "Resuelto").
    val estado: String = "Pendiente",

    val imagenUri: String? = null
)