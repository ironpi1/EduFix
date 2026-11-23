package com.example.eduffixx.`data`

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDataBases_Impl : AppDataBases() {
  private val _ticketDAO: Lazy<TicketDAO> = lazy {
    TicketDAO_Impl(this)
  }

  private val _usuarioDAO: Lazy<UsuarioDAO> = lazy {
    UsuarioDAO_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(2, "75f5a336af1ca30acfecb944558a8bc2", "53f3af61f7db1656fbd43f6e2a44deef") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `tickets` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `titulo` TEXT NOT NULL, `ubicacion` TEXT NOT NULL, `descripcion` TEXT NOT NULL, `estado` TEXT NOT NULL, `imagenUri` TEXT)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `usuarios` (`rut` TEXT NOT NULL, `nombre` TEXT NOT NULL, `contrasena` TEXT NOT NULL, `rol` TEXT NOT NULL, PRIMARY KEY(`rut`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '75f5a336af1ca30acfecb944558a8bc2')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `tickets`")
        connection.execSQL("DROP TABLE IF EXISTS `usuarios`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsTickets: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTickets.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTickets.put("titulo", TableInfo.Column("titulo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTickets.put("ubicacion", TableInfo.Column("ubicacion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTickets.put("descripcion", TableInfo.Column("descripcion", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTickets.put("estado", TableInfo.Column("estado", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTickets.put("imagenUri", TableInfo.Column("imagenUri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTickets: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesTickets: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoTickets: TableInfo = TableInfo("tickets", _columnsTickets, _foreignKeysTickets, _indicesTickets)
        val _existingTickets: TableInfo = read(connection, "tickets")
        if (!_infoTickets.equals(_existingTickets)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |tickets(com.example.eduffixx.data.Ticket).
              | Expected:
              |""".trimMargin() + _infoTickets + """
              |
              | Found:
              |""".trimMargin() + _existingTickets)
        }
        val _columnsUsuarios: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsuarios.put("rut", TableInfo.Column("rut", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsuarios.put("nombre", TableInfo.Column("nombre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsuarios.put("contrasena", TableInfo.Column("contrasena", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsUsuarios.put("rol", TableInfo.Column("rol", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsuarios: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsuarios: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUsuarios: TableInfo = TableInfo("usuarios", _columnsUsuarios, _foreignKeysUsuarios, _indicesUsuarios)
        val _existingUsuarios: TableInfo = read(connection, "usuarios")
        if (!_infoUsuarios.equals(_existingUsuarios)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |usuarios(com.example.eduffixx.data.Usuario).
              | Expected:
              |""".trimMargin() + _infoUsuarios + """
              |
              | Found:
              |""".trimMargin() + _existingUsuarios)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "tickets", "usuarios")
  }

  public override fun clearAllTables() {
    super.performClear(false, "tickets", "usuarios")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(TicketDAO::class, TicketDAO_Impl.getRequiredConverters())
    _typeConvertersMap.put(UsuarioDAO::class, UsuarioDAO_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun ticketDao(): TicketDAO = _ticketDAO.value

  public override fun usuarioDao(): UsuarioDAO = _usuarioDAO.value
}
