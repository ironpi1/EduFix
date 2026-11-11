package com.example.eduffixx.data

import androidx.room.Database
import androidx.room.RoomDatabase

// Define las entidades y la versión de la base de datos.
@Database(
    entities = [Ticket::class, Usuario::class],
    version = 2
)
abstract class AppDataBases : RoomDatabase() {

    // Proporciona acceso a las operaciones del DAO de tickets.
    abstract fun ticketDao(): TicketDAO

    // Proporciona acceso a las operaciones del DAO de usuarios.
    abstract fun usuarioDao(): UsuarioDAO
}
