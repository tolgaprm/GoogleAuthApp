package com.prmto.googleauthapp.presentation.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.prmto.googleauthapp.R
import com.prmto.googleauthapp.components.GoogleButton
import com.prmto.googleauthapp.components.MessageBar
import com.prmto.googleauthapp.domain.model.ApiResponse
import com.prmto.googleauthapp.domain.model.MessageBarState
import com.prmto.googleauthapp.ui.theme.LoadingBlue
import com.prmto.googleauthapp.util.RequestState

@ExperimentalCoilApi
@Composable
fun ProfileContent(
    apiResponse: RequestState<ApiResponse>,
    messageBarState: MessageBarState,
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    emailAddress: String,
    profilePhoto: String,
    onSignOutClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            if (apiResponse is RequestState.Loading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = LoadingBlue
                )
            } else {
                MessageBar(messageBarState = messageBarState)
            }
        }
        Column(
            modifier = Modifier.weight(9f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CentralContent(
                firstName = firstName,
                onFirstNameChanged = onFirstNameChanged,
                lastName = lastName,
                onLastNameChanged = onLastNameChanged,
                emailAddress = emailAddress,
                profilePhoto = profilePhoto,
                onSignOutClicked = onSignOutClicked
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun CentralContent(
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    emailAddress: String?,
    profilePhoto: String?,
    onSignOutClicked: () -> Unit
) {
    val imagePainter = rememberImagePainter(
        request = ImageRequest.Builder(LocalContext.current)
            .data(data = profilePhoto)
            .crossfade(durationMillis = 1000)
            .placeholder(drawableResId = R.drawable.ic_placeholder)
            .transformations(CircleCropTransformation())
            .build()
    )

    Image(
        modifier = Modifier
            .padding(bottom = 40.dp)
            .size(150.dp)
            .clip(CircleShape),
        painter = imagePainter,
        contentDescription = "Profile Photo"
    )

    OutlinedTextField(
        value = firstName,
        onValueChange = { onFirstNameChanged(it) },
        label = { Text(text = "First Name") },
        textStyle = MaterialTheme.typography.body1,
        singleLine = true
    )
    OutlinedTextField(
        value = lastName,
        onValueChange = { onLastNameChanged(it) },
        label = { Text(text = "Last Name") },
        textStyle = MaterialTheme.typography.body1,
        singleLine = true
    )
    OutlinedTextField(
        value = emailAddress.toString(),
        onValueChange = { },
        label = { Text(text = "Email Address") },
        textStyle = MaterialTheme.typography.body1,
        singleLine = true,
        enabled = false
    )
    GoogleButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        primaryText = "Sign Out",
        secondaryText = "Sign Out",
        onClick = onSignOutClicked
    )
}