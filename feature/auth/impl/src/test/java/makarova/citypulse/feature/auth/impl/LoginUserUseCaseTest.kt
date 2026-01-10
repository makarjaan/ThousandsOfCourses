package makarova.citypulse.feature.auth.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.auth.impl.usecase.LoginUserUseCaseImpl
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUserUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val authRepository: AuthRepository = mockk()
    private lateinit var useCase: LoginUserUseCaseImpl

    @Before
    fun setup() {
        useCase = LoginUserUseCaseImpl(
            authRepository = authRepository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `login returns true when repository login succeeds`() =  testScope.runTest {
        val email = "test@mail.com"
        val password = "password123"

        coEvery { authRepository.login(email, password) } returns true

        val result = useCase(email, password)

        assertTrue(result)
        coVerify { authRepository.login(email, password) }
    }

    @Test
    fun `login returns false when repository login fails`() = testScope.runTest {
        val email = "test@mail.com"
        val password = "wrong"

        coEvery { authRepository.login(email, password) } returns false

        val result = useCase(email, password)

        assertFalse(result)
        coVerify { authRepository.login(email, password) }
    }
}
