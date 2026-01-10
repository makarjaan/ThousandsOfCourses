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
import makarova.citypulse.feature.auth.impl.usecase.RegisterUserUseCaseImpl
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterUserUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val authRepository: AuthRepository = mockk()
    private lateinit var useCase: RegisterUserUseCaseImpl

    @Before
    fun setup() {
        useCase = RegisterUserUseCaseImpl(
            authRepository = authRepository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `register returns true when repository registers user`() =  testScope.runTest {
        val email = "test@mail.com"
        val name = "Test User"
        val password = "password123"

        coEvery {
            authRepository.register(email, name, password)
        } returns true

        val result = useCase(email, name, password)

        assertTrue(result)
        coVerify { authRepository.register(email, name, password) }
    }

    @Test
    fun `register returns false when repository fails`() = testScope.runTest {
        val email = "test@mail.com"
        val name = "Test User"
        val password = "password123"

        coEvery {
            authRepository.register(email, name, password)
        } returns false

        val result = useCase(email, name, password)

        assertFalse(result)
        coVerify { authRepository.register(email, name, password) }
    }
}
