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
import makarova.citypulse.feature.favorite.impl.usecase.ToggleFavoriteUseCaseImpl
import makarova.citypulse.feature.main.api.model.EventModel
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ToggleFavoriteUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: FavoriteEventsRepository = mockk()

    private lateinit var useCase: ToggleFavoriteUseCaseImpl

    @Before
    fun setup() {
        useCase = ToggleFavoriteUseCaseImpl(
            repository = repository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `returns true when event added to favorites`() = testScope.runTest {
        val email = "user@mail.com"
        val event = EventModel(
            id = "1",
            title = "Event",
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

        coEvery { repository.toggleFavorite(event, email) } returns true

        val result = useCase(event, email)

        assertTrue(result)
        coVerify { repository.toggleFavorite(event, email) }
    }

    @Test
    fun `returns false when event removed from favorites`() = testScope.runTest {
        val email = "user@mail.com"
        val event = EventModel(
            id = "1",
            title = "Event",
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

        coEvery { repository.toggleFavorite(event, email) } returns false

        val result = useCase(event, email)

        assertFalse(result)
        coVerify { repository.toggleFavorite(event, email) }
    }
}
