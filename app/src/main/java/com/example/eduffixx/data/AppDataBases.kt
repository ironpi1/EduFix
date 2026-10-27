package com.example.eduffixx.data

import androidx.room.Database
import androidx.room.RoomDatabase

// Define las entidades y la versión de la base de datos.
@Database(
    entities = [Ticket::class],
    version = 1
)
abstract class AppDataBases : RoomDatabase() {

    // Proporciona acceso a las operaciones del DAO.
    abstract fun ticketDao(): TicketDAO
}