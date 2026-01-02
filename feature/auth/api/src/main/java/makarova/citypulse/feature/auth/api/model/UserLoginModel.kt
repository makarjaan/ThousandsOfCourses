package makarova.citypulse.feature.auth.api.model

import makarova.citypulse.utils.Constants

data class UserLoginModel (
    val login: String,
    val password: String
) {
    companion object {
        val EMPTY = UserLoginModel(
            login = Constants.EMPTY_STRING,
            password = Constants.EMPTY_STRING
        )
    }
}