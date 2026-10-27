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
import com.example.eduffixx.ui.theme.EduFfixXTheme
import kotlinx.coroutines.launch

enum class Pantalla {
    LOGIN,
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
        ).build()

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
                                onIngresar = { rol, nombre ->
                                    val nombreFinal = nombre.ifBlank { "Anónimo" }
                                    rolUsuario = rol
                                    nombreUsuario = nombreFinal
                                    pantallaActual = Pantalla.LISTA
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
