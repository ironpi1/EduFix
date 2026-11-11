package com.example.eduffixx

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.eduffixx.data.AppDataBases
import com.example.eduffixx.data.Ticket
import com.example.eduffixx.data.Usuario
import com.example.eduffixx.ui.theme.EduFfixXTheme
import kotlinx.coroutines.launch

enum class Pantalla {
    LOGIN,
    REGISTRO,
    LISTA,
    FORMULARIO
}

class MainActivity : ComponentActivity() {

    private lateinit var db: AppDataBases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBases::class.java,
            "eduffix_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        enableEdgeToEdge()
        setContent {
            EduFfixXTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var pantallaActual by remember { mutableStateOf(Pantalla.LOGIN) }
                    var nombreUsuario by remember { mutableStateOf("") }
                    var rolUsuario by remember { mutableStateOf("") }

                    var listaTickets by remember { mutableStateOf<List<Ticket>>(emptyList()) }
                    val coroutineScope = rememberCoroutineScope()

                    fun cargarTickets() {
                        coroutineScope.launch {
                            listaTickets = db.ticketDao().listar()
                        }
                    }

                    LaunchedEffect(pantallaActual) {
                        if (pantallaActual == Pantalla.LISTA) {
                            cargarTickets()
                        }
                    }

                    when (pantallaActual) {
                        Pantalla.LOGIN -> {
                            PantallaLogin(
                                onIngresar = { rut, contrasena ->
                                    coroutineScope.launch {
                                        val usuario = db.usuarioDao().iniciar(rut, contrasena)
                                        if (usuario != null) {
                                            nombreUsuario = usuario.nombre
                                            rolUsuario = usuario.rol
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Bienvenido ${usuario.nombre}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            pantallaActual = Pantalla.LISTA
                                        } else {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "RUT o contraseña incorrectos",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                },
                                onIrARegistro = {
                                    pantallaActual = Pantalla.REGISTRO
                                }
                            )
                        }

                        Pantalla.REGISTRO -> {
                            PantallaRegistro(
                                onRegistrar = { nombre, rut, contrasena, rol ->
                                    coroutineScope.launch {
                                        val nuevoUsuario = Usuario(
                                            rut = rut,
                                            nombre = nombre,
                                            contrasena = contrasena,
                                            rol = rol
                                        )
                                        db.usuarioDao().registrar(nuevoUsuario)
                                        nombreUsuario = nombre
                                        rolUsuario = rol
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Cuenta registrada con éxito",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        pantallaActual = Pantalla.LISTA
                                    }
                                },
                                onVolverLogin = {
                                    pantallaActual = Pantalla.LOGIN
                                }
                            )
                        }

                        Pantalla.LISTA -> {
                            PantallaListaTickets(
                                tickets = listaTickets,
                                nombreUsuario = nombreUsuario,
                                rol = rolUsuario,
                                onCambiarEstado = { id, nuevoEstado ->
                                    coroutineScope.launch {
                                        db.ticketDao().actualizarEstado(id, nuevoEstado)
                                        cargarTickets()
                                    }
                                },
                                onBorrarTicket = { id ->
                                    coroutineScope.launch {
                                        db.ticketDao().eliminar(id)
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Reporte eliminado",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        cargarTickets()
                                    }
                                },
                                onNuevoReporte = {
                                    pantallaActual = Pantalla.FORMULARIO
                                },
                                onCerrarSesion = {
                                    pantallaActual = Pantalla.LOGIN
                                }
                            )
                        }

                        Pantalla.FORMULARIO -> {
                            PantallaFormulario(
                                onEnviarTicket = { titulo, ubicacion, descripcion ->
                                    coroutineScope.launch {
                                        val nuevoTicket = Ticket(
                                            titulo = titulo,
                                            ubicacion = ubicacion,
                                            descripcion = descripcion
                                        )
                                        db.ticketDao().insertar(nuevoTicket)
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Reporte enviado con éxito",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        pantallaActual = Pantalla.LISTA
                                    }
                                },
                                onVolver = {
                                    pantallaActual = Pantalla.LISTA
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
