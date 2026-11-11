package com.example.eduffixx

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
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
fun PantallaLogin(
    onIngresar: (rut: String, contrasena: String) -> Unit,
    onIrARegistro: () -> Unit = {}
) {
    var rut by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var errorMensaje by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Colegio San Bernardo") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(32.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Gestión de Incidencias", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = rut,
                onValueChange = {
                    rut = it
                    if (errorMensaje.isNotEmpty()) errorMensaje = ""
                },
                label = { Text("RUT o Identificador") },
                placeholder = { Text("Ej: 12.345.678-9") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

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

            if (errorMensaje.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMensaje,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (rut.isBlank() || contrasena.isBlank()) {
                        errorMensaje = "Ingresa tu RUT y contraseña."
                    } else {
                        onIngresar(rut.trim(), contrasena.trim())
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Sesión")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = onIrARegistro,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("¿No tienes una cuenta? Regístrate aquí")
            }
        }
    }
}
