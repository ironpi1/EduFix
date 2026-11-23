package com.example.eduffixx

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFormulario(
    onEnviarTicket: (titulo: String, ubicacion: String, descripcion: String) -> Unit,
    onVolver: () -> Unit = {}
) {
    var titulo by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reportar Incidencia") },
                navigationIcon = {
                    TextButton(onClick = onVolver) {
                        Text("Cerrar sesión")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Formulario de Reporte de Daños",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Completa los campos para notificar sobre daños estructurales o deficiencias en el colegio San Bernardo.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            OutlinedTextField(
                value = titulo,
                onValueChange = {
                    titulo = it
                    if (errorMensaje.isNotEmpty()) errorMensaje = ""
                },
                label = { Text("Título del problema") },
                placeholder = { Text("Ej: Silla rota, Ventana quebrada") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = ubicacion,
                onValueChange = {
                    ubicacion = it
                    if (errorMensaje.isNotEmpty()) errorMensaje = ""
                },
                label = { Text("Ubicación en el colegio") },
                placeholder = { Text("Ej: Sala 4B, Patio principal, Baños 1er piso") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = {
                    descripcion = it
                    if (errorMensaje.isNotEmpty()) errorMensaje = ""
                },
                label = { Text("Descripción del problema") },
                placeholder = { Text("Describe en detalle la deficiencia o daño observado...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                maxLines = 6
            )

            if (errorMensaje.isNotEmpty()) {
                Text(
                    text = errorMensaje,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (titulo.isBlank() || ubicacion.isBlank() || descripcion.isBlank()) {
                        errorMensaje = "Por favor, completa todos los campos."
                    } else {
                        onEnviarTicket(titulo.trim(), ubicacion.trim(), descripcion.trim())
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar Reporte")
            }
        }
    }
}
