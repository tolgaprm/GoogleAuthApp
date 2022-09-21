package com.prmto.googleauthapp.presentation.screen.profile

import android.app.Activity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.google.android.gms.auth.api.identity.Identity
import com.prmto.googleauthapp.domain.model.ApiResponse
import com.prmto.googleauthapp.navigation.Screen
import com.prmto.googleauthapp.util.RequestState

@ExperimentalCoilApi
@Composable
fun ProfileScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    val apiResponse by profileViewModel.apiResponse
    val clearSessionResponse by profileViewModel.clearSessionResponse
    val messageBarState by profileViewModel.messageBarState

    val user by profileViewModel.user
    val firstName by profileViewModel.firstName
    val lastName by profileViewModel.lastName

    val activity = LocalContext.current as Activity

    Scaffold(
        topBar = {
            ProfileTopBar(
                onSave = profileViewModel::updateUserInfo,
                onDeleteAllConfirmed = {}
            )
        },
        content = {
            ProfileContent(
                apiResponse = apiResponse,
                messageBarState = messageBarState,
                firstName = firstName,
                onFirstNameChanged = profileViewModel::updateFirstName,
                lastName = lastName,
                onLastNameChanged = profileViewModel::updateLastName,
                emailAddress = user?.emailAddress,
                profilePhoto = user?.profilePhoto,
                onSignOutClicked = profileViewModel::clearSession
            )
        }
    )



    LaunchedEffect(key1 = clearSessionResponse) {
        if (clearSessionResponse is RequestState.Success &&
            (clearSessionResponse as RequestState.Success<ApiResponse>).data.success
        ) {
            val oneTapClient = Identity.getSignInClient(activity)
            oneTapClient.signOut()
            profileViewModel.saveSignedInState(signedIn = false)
            navigateToLoginScreen(navController = navController)

        }
    }
}

private fun navigateToLoginScreen(navController: NavHostController) {
    navController.navigate(Screen.Login.route) {
        popUpTo(Screen.Profile.route) {
            inclusive = true
        }
    }
}