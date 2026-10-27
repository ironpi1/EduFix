package com.example.eduffixx

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.eduffixx.data.Ticket

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaListaTickets(
    tickets: List<Ticket>,
    nombreUsuario: String,
    rol: String,
    onCambiarEstado: (id: Int, nuevoEstado: String) -> Unit = { _, _ -> },
    onBorrarTicket: (id: Int) -> Unit = {},
    onNuevoReporte: () -> Unit = {},
    onCerrarSesion: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Colegio San Bernardo - Reportes") },
                actions = {
                    TextButton(onClick = onCerrarSesion) {
                        Text("Salir")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            if (rol == "SOLICITANTE") {
                ExtendedFloatingActionButton(
                    onClick = onNuevoReporte,
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Text("+ Nuevo Reporte")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Usuario: $nombreUsuario ($rol)",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (tickets.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay reportes registrados.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(tickets, key = { it.id }) { ticket ->
                        CardTicket(
                            ticket = ticket,
                            esMantenimiento = rol == "MANTENIMIENTO",
                            onCambiarEstado = { nuevoEstado ->
                                onCambiarEstado(ticket.id, nuevoEstado)
                            },
                            onBorrarTicket = {
                                onBorrarTicket(ticket.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardTicket(
    ticket: Ticket,
    esMantenimiento: Boolean,
    onCambiarEstado: (nuevoEstado: String) -> Unit,
    onBorrarTicket: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ticket.titulo,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = ticket.estado,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (ticket.estado == "Resuelto") Color(0xFF2E7D32) else Color(0xFFD32F2F)
                )
            }

            Text(
                text = "Ubicación: ${ticket.ubicacion}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = ticket.descripcion,
                style = MaterialTheme.typography.bodySmall
            )

            if (esMantenimiento) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = onBorrarTicket,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Borrar")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            val nuevo = if (ticket.estado == "Resuelto") "Pendiente" else "Resuelto"
                            onCambiarEstado(nuevo)
                        }
                    ) {
                        Text(if (ticket.estado == "Resuelto") "Marcar Pendiente" else "Marcar Resuelto")
                    }
                }
            }
        }
    }
}
