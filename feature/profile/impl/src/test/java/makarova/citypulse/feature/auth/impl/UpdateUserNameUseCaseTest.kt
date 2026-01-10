package makarova.citypulse.feature.auth.impl

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import makarova.citypulse.feature.profile.impl.usecase.UpdateUserNameUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.auth.api.repository.AuthRepository
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class UpdateUserNameUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: AuthRepository = mockk()

    private lateinit var useCase: UpdateUserNameUseCaseImpl

    @Before
    fun setup() {
        useCase = UpdateUserNameUseCaseImpl(
            repository = repository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `returns true when username updated successfully`() = testScope.runTest {
        val email = "test@mail.com"
        val newName = "Анна"

        coEvery {
            repository.updateUserName(email, newName)
        } returns true

        val result = useCase(email, newName)

        assertTrue(result)
        coVerify(exactly = 1) {
            repository.updateUserName(email, newName)
        }
    }

    @Test
    fun `returns false when update failed`() = testScope.runTest {
        val email = "test@mail.com"
        val newName = "Анна"

        coEvery {
            repository.updateUserName(email, newName)
        } returns false

        val result = useCase(email, newName)

        assertFalse(result)
        coVerify(exactly = 1) {
            repository.updateUserName(email, newName)
        }
    }
}
