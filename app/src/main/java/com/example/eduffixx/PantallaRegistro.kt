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
    var confirmarContrasena by remember { mutableStateOf("") }
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
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
                    errorMensaje = ""
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
                    errorMensaje = ""
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
                    errorMensaje = ""
                },
                label = { Text("Contraseña (mín. 6 caracteres)") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = confirmarContrasena,
                onValueChange = {
                    confirmarContrasena = it
                    errorMensaje = ""
                },
                label = { Text("Confirmar Contraseña") },
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

            Spacer(modifier = Modifier.height(6.dp))

            Button(
                onClick = {
                    val nombreLimpio = nombre.trim()
                    val rutLimpio = rut.trim()
                    val rutRegex = Regex("^\\d{1,2}(\\.?\\d{3}){2}-[0-9kK]$")

                    when {
                        nombreLimpio.length < 3 -> {
                            errorMensaje = "El nombre debe tener al menos 3 caracteres."
                        }
                        !rutRegex.matches(rutLimpio) -> {
                            errorMensaje = "Formato de RUT inválido (Ej: 12.345.678-9 o 12345678-9)."
                        }
                        contrasena.length < 6 -> {
                            errorMensaje = "La contraseña debe tener al menos 6 caracteres."
                        }
                        contrasena != confirmarContrasena -> {
                            errorMensaje = "Las contraseñas no coinciden."
                        }
                        else -> {
                            onRegistrar(nombreLimpio, rutLimpio, contrasena.trim(), rolSeleccionado)
                        }
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