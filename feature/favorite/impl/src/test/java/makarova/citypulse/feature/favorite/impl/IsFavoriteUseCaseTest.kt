package makarova.citypulse.feature.favorite.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.favorite.api.repository.FavoriteEventsRepository
import makarova.citypulse.feature.favorite.impl.usecase.IsFavoriteUseCaseImpl
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class IsFavoriteUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: FavoriteEventsRepository = mockk()

    private lateinit var useCase: IsFavoriteUseCaseImpl

    @Before
    fun setup() {
        useCase = IsFavoriteUseCaseImpl(
            repository = repository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `returns true when event is favorite`() = testScope.runTest {
        val eventId = "123"
        val email = "user@mail.com"

        coEvery { repository.isFavorite(eventId, email) } returns true

        val result = useCase(eventId, email)

        assertTrue(result)
        coVerify { repository.isFavorite(eventId, email) }
    }

    @Test
    fun `returns false when event is not favorite`() = testScope.runTest {
        val eventId = "123"
        val email = "user@mail.com"

        coEvery { repository.isFavorite(eventId, email) } returns false

        val result = useCase(eventId, email)

        assertFalse(result)
        coVerify { repository.isFavorite(eventId, email) }
    }
}
