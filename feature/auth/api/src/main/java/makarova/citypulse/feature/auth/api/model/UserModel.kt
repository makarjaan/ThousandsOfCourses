package makarova.citypulse.feature.auth.api.model

import makarova.citypulse.utils.Constants

data class UserModel (
    val login: String,
    val name: String,
    val password: String,
    val avatarUrl: String? = null
) {
    companion object {
        val EMPTY = UserModel(
            login = Constants.EMPTY_STRING,
            name = Constants.EMPTY_STRING,
            password = Constants.EMPTY_STRING,
            avatarUrl = null
        )
    }
}