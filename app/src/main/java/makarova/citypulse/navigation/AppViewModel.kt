package makarova.citypulse.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import makarova.citypulse.feature.auth.api.usecase.CheckAuthUseCase
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val checkAuthUseCase: CheckAuthUseCase
) : ViewModel() {

    private val _isAuthorized = MutableStateFlow<Boolean?>(null)
    val isAuthorized: StateFlow<Boolean?> = _isAuthorized

    init {
        viewModelScope.launch {
            _isAuthorized.value = checkAuthUseCase()
        }
    }
}