package com.example.eduffixx.`data`

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class TicketDAO_Impl(
  __db: RoomDatabase,
) : TicketDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfTicket: EntityInsertAdapter<Ticket>
  init {
    this.__db = __db
    this.__insertAdapterOfTicket = object : EntityInsertAdapter<Ticket>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `tickets` (`id`,`titulo`,`ubicacion`,`descripcion`,`estado`,`imagenUri`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Ticket) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.titulo)
        statement.bindText(3, entity.ubicacion)
        statement.bindText(4, entity.descripcion)
        statement.bindText(5, entity.estado)
        val _tmpImagenUri: String? = entity.imagenUri
        if (_tmpImagenUri == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpImagenUri)
        }
      }
    }
  }

  public override suspend fun insertar(ticket: Ticket): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfTicket.insert(_connection, ticket)
  }

  public override suspend fun listar(): List<Ticket> {
    val _sql: String = "SELECT * FROM tickets"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitulo: Int = getColumnIndexOrThrow(_stmt, "titulo")
        val _columnIndexOfUbicacion: Int = getColumnIndexOrThrow(_stmt, "ubicacion")
        val _columnIndexOfDescripcion: Int = getColumnIndexOrThrow(_stmt, "descripcion")
        val _columnIndexOfEstado: Int = getColumnIndexOrThrow(_stmt, "estado")
        val _columnIndexOfImagenUri: Int = getColumnIndexOrThrow(_stmt, "imagenUri")
        val _result: MutableList<Ticket> = mutableListOf()
        while (_stmt.step()) {
          val _item: Ticket
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitulo: String
          _tmpTitulo = _stmt.getText(_columnIndexOfTitulo)
          val _tmpUbicacion: String
          _tmpUbicacion = _stmt.getText(_columnIndexOfUbicacion)
          val _tmpDescripcion: String
          _tmpDescripcion = _stmt.getText(_columnIndexOfDescripcion)
          val _tmpEstado: String
          _tmpEstado = _stmt.getText(_columnIndexOfEstado)
          val _tmpImagenUri: String?
          if (_stmt.isNull(_columnIndexOfImagenUri)) {
            _tmpImagenUri = null
          } else {
            _tmpImagenUri = _stmt.getText(_columnIndexOfImagenUri)
          }
          _item = Ticket(_tmpId,_tmpTitulo,_tmpUbicacion,_tmpDescripcion,_tmpEstado,_tmpImagenUri)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun actualizarEstado(id: Int, nuevoEstado: String) {
    val _sql: String = "UPDATE tickets SET estado = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, nuevoEstado)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun eliminar(id: Int) {
    val _sql: String = "DELETE FROM tickets WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun eliminarTodos() {
    val _sql: String = "DELETE FROM tickets"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
