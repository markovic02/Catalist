package rs.raf.rma.catalist.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import rs.raf.rma.catalist.auth.AuthStore
import javax.inject.Inject

@HiltViewModel
class LoginPageViewModel @Inject constructor(
    private val authStore: AuthStore
): ViewModel(){

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    fun saveUserData(userData: String) {
        viewModelScope.launch {
            authStore.updateAuthData(userData)
        }
    }
}