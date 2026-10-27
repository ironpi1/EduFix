package com.example.eduffixx

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLogin(
    onIngresar: (rol: String, nombre: String) -> Unit
) {
    var nombreUsuario by remember { mutableStateOf("") }

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

            TextField(
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = { Text("Nombre o Identificador") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onIngresar("SOLICITANTE", nombreUsuario) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar como Solicitante")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { onIngresar("MANTENIMIENTO", nombreUsuario) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text("Ingresar como Mantenimiento")
            }
        }
    }
}