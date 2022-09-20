package com.prmto.googleauthapp.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DisplayAlertDialog(
    title: String = "Delete your account?",
    message: String = "Are you sure you want to delete your account?",
    openDialog: Boolean,
    onYesClick: () -> Unit,
    onDialogClosed: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(onClick = {
                    onYesClick()
                    onDialogClosed()
                }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { onDialogClosed() }) {
                    Text(text = "No")
                }
            },
            onDismissRequest = { onDialogClosed() },
        )
    }
}

@Preview
@Composable
fun DisplayAlertDialogPreview() {
    DisplayAlertDialog(openDialog = true, onYesClick = {  }, onDialogClosed = {})
}