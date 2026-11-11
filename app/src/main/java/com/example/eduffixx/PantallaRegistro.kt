package com.example.eduffixx

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRegistro(
    onRegistrar: (nombre: String, rut: String, contrasena: String, rol: String) -> Unit,
    onVolverLogin: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var rut by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var rolSeleccionado by remember { mutableStateOf("SOLICITANTE") }
    var errorMensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Registro - Colegio San Bernardo") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Crear nueva cuenta",
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    if (errorMensaje.isNotEmpty()) errorMensaje = ""
                },
                label = { Text("Nombre Completo") },
                placeholder = { Text("Ej: Juan Pérez") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = rut,
                onValueChange = {
                    rut = it
                    if (errorMensaje.isNotEmpty()) errorMensaje = ""
                },
                label = { Text("RUT / Identificador") },
                placeholder = { Text("Ej: 12.345.678-9") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = contrasena,
                onValueChange = {
                    contrasena = it
                    if (errorMensaje.isNotEmpty()) errorMensaje = ""
                },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Text(
                text = "Selecciona tu Rol:",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FilterChip(
                    selected = rolSeleccionado == "SOLICITANTE",
                    onClick = { rolSeleccionado = "SOLICITANTE" },
                    label = { Text("Solicitante") }
                )
                FilterChip(
                    selected = rolSeleccionado == "MANTENIMIENTO",
                    onClick = { rolSeleccionado = "MANTENIMIENTO" },
                    label = { Text("Mantenimiento") }
                )
            }

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
                    if (nombre.isBlank() || rut.isBlank() || contrasena.isBlank()) {
                        errorMensaje = "Por favor, completa todos los campos para registrarte."
                    } else {
                        onRegistrar(nombre.trim(), rut.trim(), contrasena.trim(), rolSeleccionado)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }

            TextButton(
                onClick = onVolverLogin,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("¿Ya tienes una cuenta? Iniciar Sesión")
            }
        }
    }
}
