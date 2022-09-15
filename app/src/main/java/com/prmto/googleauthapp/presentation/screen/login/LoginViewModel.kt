package com.prmto.googleauthapp.presentation.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.googleauthapp.domain.model.MessageBarState
import com.prmto.googleauthapp.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _signedInState = mutableStateOf<Boolean>(false)
    val signedInState: State<Boolean> get() = _signedInState

    private val _messageBarState = mutableStateOf<MessageBarState>(MessageBarState())
    val messageBarState: State<MessageBarState> get() = _messageBarState

    init {
        viewModelScope.launch {
            repository.readSignedInState().collect { completed ->
                _signedInState.value = completed
            }
        }
    }

    fun saveSignedInState(signedIn: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveSignedInState(signedIn = signedIn)
        }
    }

    fun updateMessageBarState(){
        _messageBarState.value = MessageBarState(error = GoogleAccountNotFoundException())
    }
}

class GoogleAccountNotFoundException(
    override val message: String?="Google Account Not Found."
):Exception()