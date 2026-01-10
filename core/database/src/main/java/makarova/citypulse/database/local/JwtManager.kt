package makarova.citypulse.database.local

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JwtManager @Inject constructor() {

    private val secretKey = "very_secret_key"
    private val algorithm = Algorithm.HMAC256(secretKey)

    private val tokenLifetimeMillis = 60 * 60 * 1000

    fun generateToken(email: String): String {
        val now = Date()
        val expiresAt = Date(now.time + tokenLifetimeMillis)

        return JWT.create()
            .withSubject(email)
            .withIssuedAt(now)
            .withExpiresAt(expiresAt)
            .sign(algorithm)
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            val decoded = JWT
                .require(algorithm)
                .build()
                .verify(token)

            decoded.expiresAt.after(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getEmail(token: String): String? {
        return try {
            JWT.require(algorithm).build().verify(token).subject
        } catch (e: Exception) {
            null
        }
    }
}
