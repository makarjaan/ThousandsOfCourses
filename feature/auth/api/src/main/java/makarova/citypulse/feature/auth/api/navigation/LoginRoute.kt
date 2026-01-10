package makarova.citypulse.feature.auth.api.navigation

import makarova.citypulse.navigation.Destination
import makarova.citypulse.utils.Routes

object LoginRoute : Destination {
    override val destination = Routes.LOGIN
}

object RegistrationRoute : Destination {
    override val destination = Routes.REGISTER
}
