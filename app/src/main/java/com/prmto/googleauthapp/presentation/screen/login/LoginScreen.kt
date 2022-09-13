package com.prmto.googleauthapp.presentation.screen.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.prmto.googleauthapp.domain.model.MessageBarState

@Composable
fun LoginScreen(
    navController: NavHostController
) {

    Scaffold(
        topBar = { LoginTopBar() },
        content = {
            LoginContent(
                signedInState = false,
                messageBarState = MessageBarState(),
                onButtonClicked = {}
            )
        }
    )

}