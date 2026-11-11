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
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class UsuarioDAO_Impl(
  __db: RoomDatabase,
) : UsuarioDAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfUsuario: EntityInsertAdapter<Usuario>
  init {
    this.__db = __db
    this.__insertAdapterOfUsuario = object : EntityInsertAdapter<Usuario>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `usuarios` (`rut`,`nombre`,`contrasena`,`rol`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Usuario) {
        statement.bindText(1, entity.rut)
        statement.bindText(2, entity.nombre)
        statement.bindText(3, entity.contrasena)
        statement.bindText(4, entity.rol)
      }
    }
  }

  public override suspend fun registrar(usuario: Usuario): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfUsuario.insert(_connection, usuario)
  }

  public override suspend fun iniciar(rut: String, contrasena: String): Usuario? {
    val _sql: String = "SELECT * FROM usuarios WHERE rut = ? AND contrasena = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, rut)
        _argIndex = 2
        _stmt.bindText(_argIndex, contrasena)
        val _columnIndexOfRut: Int = getColumnIndexOrThrow(_stmt, "rut")
        val _columnIndexOfNombre: Int = getColumnIndexOrThrow(_stmt, "nombre")
        val _columnIndexOfContrasena: Int = getColumnIndexOrThrow(_stmt, "contrasena")
        val _columnIndexOfRol: Int = getColumnIndexOrThrow(_stmt, "rol")
        val _result: Usuario?
        if (_stmt.step()) {
          val _tmpRut: String
          _tmpRut = _stmt.getText(_columnIndexOfRut)
          val _tmpNombre: String
          _tmpNombre = _stmt.getText(_columnIndexOfNombre)
          val _tmpContrasena: String
          _tmpContrasena = _stmt.getText(_columnIndexOfContrasena)
          val _tmpRol: String
          _tmpRol = _stmt.getText(_columnIndexOfRol)
          _result = Usuario(_tmpRut,_tmpNombre,_tmpContrasena,_tmpRol)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
