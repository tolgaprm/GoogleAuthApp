package com.prmto.googleauthapp.presentation.screen.login

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.prmto.googleauthapp.ui.theme.topAppBarBackgroundColor
import com.prmto.googleauthapp.ui.theme.topAppBarContentColor

@Composable
fun LoginTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Sign in", color =
                MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Preview
@Composable
fun LoginTopBarPreview() {
    LoginTopBar()
}