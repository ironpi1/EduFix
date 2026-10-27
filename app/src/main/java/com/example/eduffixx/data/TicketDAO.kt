package com.example.eduffixx.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TicketDAO {

    // Inserta un ticket en la tabla.
    @Insert
    suspend fun insertar(ticket: Ticket)

    // Recupera todos los tickets.
    @Query("SELECT * FROM tickets")
    suspend fun listar(): List<Ticket>

    // Actualiza el estado del reporte ("Pendiente" o "Resuelto").
    @Query("UPDATE tickets SET estado = :nuevoEstado WHERE id = :id")
    suspend fun actualizarEstado(id: Int, nuevoEstado: String)

    // Elimina todos los registros de la tabla tickets.
    @Query("DELETE FROM tickets")
    suspend fun eliminarTodos()
}