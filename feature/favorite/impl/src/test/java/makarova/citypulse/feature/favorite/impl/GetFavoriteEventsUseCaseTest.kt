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
import makarova.citypulse.feature.favorite.impl.usecase.GetFavoriteEventsUseCaseImpl
import makarova.citypulse.feature.main.api.model.EventModel
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetFavoriteEventsUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: FavoriteEventsRepository = mockk()

    private lateinit var useCase: GetFavoriteEventsUseCaseImpl

    @Before
    fun setup() {
        useCase = GetFavoriteEventsUseCaseImpl(
            repository = repository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `returns favorite events for user`() = testScope.runTest {
        val email = "user@mail.com"

        val events = listOf(
            EventModel(
                id = "1",
                title = "Event 1",
                city = "Москва",
                category = "music",
                dateStart = 2L,
                place = "d",
                address = "a",
                imageUrl = "aa",
                isFree = false,
                favoritesCount = 7,
                shortTitle = "",
                description = "",
                fullDescription = "",
                price = "",
                ageRestriction = "",
                dateEnd = null,
                locationSlug = null,
                siteUrl = ""
            )
        )

        coEvery { repository.getFavoriteEventsByUser(email) } returns events

        val result = useCase(email)

        assertEquals(events, result)
        coVerify(exactly = 1) { repository.getFavoriteEventsByUser(email) }
    }
}
