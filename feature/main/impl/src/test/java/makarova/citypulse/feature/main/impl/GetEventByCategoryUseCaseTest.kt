package makarova.citypulse.feature.main.impl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.main.api.model.EventModel
import makarova.citypulse.feature.main.api.repository.EventsRepository
import makarova.citypulse.feature.main.impl.usecasae.GetEventByCategoryUseCaseImpl
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetEventByCategoryUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: EventsRepository = mockk()

    private lateinit var useCase: GetEventByCategoryUseCaseImpl

    @Before
    fun setup() {
        useCase = GetEventByCategoryUseCaseImpl(repository, testDispatcher)
    }

    @Test
    fun `returns events by category`() = testScope.runTest {
        val events = listOf(
            EventModel(
                id = "1",
                title = "Concert",
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

        coEvery {
            repository.getEventsByCategory(
                city = "Москва",
                category = "music",
                page = 1,
                pageSize = 10
            )
        } returns events

        val result = useCase(
            city = "Москва",
            category = "music",
            page = 1
        )

        assertEquals(events, result)
        coVerify { repository.getEventsByCategory("Москва", "music", 1, 10) }
    }
}
