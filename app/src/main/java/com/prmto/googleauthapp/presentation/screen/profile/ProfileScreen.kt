package com.prmto.googleauthapp.presentation.screen.profile

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.prmto.googleauthapp.domain.model.ApiResponse
import com.prmto.googleauthapp.domain.model.MessageBarState
import com.prmto.googleauthapp.util.RequestState

@ExperimentalCoilApi
@Composable
fun ProfileScreen(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            ProfileTopBar(
                onSave = {  },
                onDeleteAllConfirmed = {}
            )
        },
        content = {
            ProfileContent(
                apiResponse = RequestState.Success(ApiResponse(success = true)),
                messageBarState = MessageBarState(),
                firstName = "",
                onFirstNameChanged = {},
                lastName = "",
                onLastNameChanged = {},
                emailAddress = "",
                profilePhoto = "",
                onSignOutClicked = {}
            )
        }
    )
}