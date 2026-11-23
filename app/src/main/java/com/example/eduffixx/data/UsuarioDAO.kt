package com.example.eduffixx.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registrar(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE rut = :rut AND contrasena = :contrasena LIMIT 1")
    suspend fun iniciar(rut: String, contrasena: String): Usuario?
}