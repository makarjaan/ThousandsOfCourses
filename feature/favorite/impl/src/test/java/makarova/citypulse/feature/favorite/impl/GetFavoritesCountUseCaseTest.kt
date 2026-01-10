package makarova.citypulse.feature.favorite.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.favorite.api.repository.FavoriteEventsRepository
import makarova.citypulse.feature.favorite.impl.usecase.GetFavoritesCountUseCaseImpl
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetFavoritesCountUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: FavoriteEventsRepository = mockk()

    private lateinit var useCase: GetFavoritesCountUseCaseImpl

    @Before
    fun setup() {
        useCase = GetFavoritesCountUseCaseImpl(
            repository = repository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `returns favorites count`() = testScope.runTest {
        val email = "user@mail.com"

        coEvery { repository.getFavoritesCount(email) } returns 5

        val result = useCase(email)

        assertEquals(5, result)
        coVerify { repository.getFavoritesCount(email) }
    }
}
