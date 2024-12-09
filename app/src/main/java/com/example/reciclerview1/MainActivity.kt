package com.example.reciclerview1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var actionText by remember { mutableStateOf("Pantalla Principal") }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }
    var showAuthDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = actionText)

        Spacer(modifier = Modifier.height(16.dp))

        // Confirmación de Acción
        Button(
            onClick = { showConfirmationDialog = true },
            modifier = Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.White
            )
        ) {
            Text("Confirmación de Acción")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Alerta de Eliminación Permanente
        Button(
            onClick = { showDeleteDialog = true },
            modifier = Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text("Eliminar Elemento")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Alerta de Información Importante
        Button(
            onClick = { showInfoDialog = true },
            modifier = Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow,
                contentColor = Color.White
            )
        ) {
            Text("Aviso Importante")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Alerta de Autenticación Requerida
        Button(
            onClick = { showAuthDialog = true },
            modifier = Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.White
            )
        ) {
            Text("Requiere Autenticación")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Alerta de Error Crítico
        Button(
            onClick = { showErrorDialog = true },
            modifier = Modifier.size(width = 250.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text("Error Crítico")
        }

        if (showConfirmationDialog) {
            ConfirmationDialog(
                onDismiss = { showConfirmationDialog = false },
                onConfirm = {
                    actionText = "Acción Confirmada"
                    showConfirmationDialog = false
                }
            )
        }

        if (showDeleteDialog) {
            DeleteDialog(
                onDismiss = { showDeleteDialog = false },
                onConfirm = {
                    actionText = "Elemento eliminado"
                    showDeleteDialog = false
                }
            )
        }

        if (showInfoDialog) {
            InfoDialog(onDismiss = { showInfoDialog = false })
        }

        if (showAuthDialog) {
            AuthDialog(
                onDismiss = { showAuthDialog = false },
                onConfirm = {
                    actionText = "Usuario Autenticado"
                    showAuthDialog = false
                }
            )
        }

        if (showErrorDialog) {
            ErrorDialog(
                onDismiss = { showErrorDialog = false },
                onConfirm = {
                    actionText = "Intento de Reintento"
                    showErrorDialog = false
                }
            )
        }
    }
}

@Composable
fun ConfirmationDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirmación de Acción") },
        text = { Text("¿Estás seguro de que deseas continuar con esta acción?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Sí")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}

@Composable
fun DeleteDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Eliminar Elemento") },
        text = { Text("Esta acción es irreversible. ¿Deseas eliminar este elemento?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun InfoDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Aviso Importante") },
        text = { Text("Recuerda que los cambios realizados no se pueden deshacer.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Entendido")
            }
        },
        dismissButton = null
    )
}

@Composable
fun AuthDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Requiere Autenticación") },
        text = { Text("Para continuar, necesitas autenticarte de nuevo.") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Autenticar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun ErrorDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Error Crítico") },
        text = { Text("Se ha producido un error crítico. ¿Deseas intentar nuevamente?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Reintentar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
