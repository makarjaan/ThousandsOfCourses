package makarova.citypulse.feature.auth.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.auth.api.model.UserModel
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import makarova.citypulse.feature.auth.impl.usecase.GetCurrentUserUseCaseImpl
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentUserUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val authRepository: AuthRepository = mockk()
    private lateinit var useCase: GetCurrentUserUseCaseImpl

    @Before
    fun setup() {
        useCase = GetCurrentUserUseCaseImpl(
            authRepository = authRepository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `returns current user from repository`() = testScope.runTest {
        val user = UserModel(
            login = "test@mail.com",
            name = "Test User",
            password = "",
            avatarUrl = ""
        )

        coEvery { authRepository.getCurrentUser() } returns user

        val result = useCase()

        assertEquals(user, result)
        coVerify(exactly = 1) { authRepository.getCurrentUser() }
    }
}