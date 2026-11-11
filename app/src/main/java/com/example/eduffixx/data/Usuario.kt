package com.example.eduffixx.data

import androidx.room.Entity
import androidx.room.PrimaryKey


//crear tabla para usuarios
@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey
    val rut: String,          // Ej: "12.345.678-9"
    val nombre: String,       // Ej: "Juan Pérez"
    val contrasena: String,   // Contraseña ingresada
    val rol: String           // "SOLICITANTE" o "MANTENIMIENTO"
)