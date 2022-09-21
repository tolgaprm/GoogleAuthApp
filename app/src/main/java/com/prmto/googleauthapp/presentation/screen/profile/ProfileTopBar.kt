package com.prmto.googleauthapp.presentation.screen.profile

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.prmto.googleauthapp.R
import com.prmto.googleauthapp.components.DisplayAlertDialog
import com.prmto.googleauthapp.ui.theme.topAppBarBackgroundColor
import com.prmto.googleauthapp.ui.theme.topAppBarContentColor

@Composable
fun ProfileTopBar(
    onSave: () -> Unit,
    onDeleteAllConfirmed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Profile",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            ProfileTopBarActions(onSave = onSave, onDeleteAllConfirmed = onDeleteAllConfirmed)
        }
    )
}

@Composable
fun ProfileTopBarActions(
    onSave: () -> Unit,
    onDeleteAllConfirmed: () -> Unit
) {

    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        openDialog = openDialog,
        onYesClick =onDeleteAllConfirmed,
        onDialogClosed = { openDialog = false }
    )

    SaveAction(onSave = onSave)
    DeleteAllAction(onDelete = { openDialog = true })
}

@Composable
fun SaveAction(onSave: () -> Unit) {
    IconButton(onClick = onSave) {
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = "Save Icon",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAllAction(onDelete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Vertical Menu",
            tint = MaterialTheme.colors.topAppBarContentColor
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                onDelete()
            }) {
                Text(
                    text = "Delete Account",
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileTopBarPreview() {
    ProfileTopBar(onSave = {}, onDeleteAllConfirmed = {})
}