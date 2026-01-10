package makarova.citypulse.feature.detail.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.detail.api.repository.EventDetailsRepository
import makarova.citypulse.feature.detail.impl.usecase.GetEventDetailsUseCaseImpl
import makarova.citypulse.feature.main.api.model.EventModel
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetEventDetailsUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: EventDetailsRepository = mockk()

    @Test
    fun `returns event details when repository succeeds`() = testScope.runTest {

        val eventId = "event123"
        val expectedEvent = EventModel(
            id = eventId,
            title = "Test Event",
            dateStart = 2L,
            place = "d",
            address = "a",
            imageUrl = "aa",
            category = "d",
            isFree = false,
            favoritesCount = 7,
            city = "kzn",
            shortTitle = "",
            description = "",
            fullDescription = "",
            price = "",
            ageRestriction = "",
            dateEnd = null,
            locationSlug = null,
            siteUrl = ""
        )

        val useCase = GetEventDetailsUseCaseImpl(
            repository = repository,
            ioDispatcher = testDispatcher
        )

        coEvery { repository.getEventDetails(eventId) } returns expectedEvent

        val result = useCase(eventId)

        assertEquals(expectedEvent, result)
        coVerify(exactly = 1) { repository.getEventDetails(eventId) }
    }

    @Test
    fun `handles empty event id`() = testScope.runTest {

        val emptyEventId = ""
        val emptyEvent = EventModel(
            id = emptyEventId,
            title = "Test Event",
            dateStart = 2L,
            place = "d",
            address = "a",
            imageUrl = "aa",
            category = "d",
            isFree = false,
            favoritesCount = 7,
            city = "kzn",
            shortTitle = "",
            description = "",
            fullDescription = "",
            price = "",
            ageRestriction = "",
            dateEnd = null,
            locationSlug = null,
            siteUrl = ""
        )

        val useCase = GetEventDetailsUseCaseImpl(
            repository = repository,
            ioDispatcher = testDispatcher
        )

        coEvery { repository.getEventDetails(emptyEventId) } returns emptyEvent
        val result = useCase(emptyEventId)

        assertEquals(emptyEvent, result)
        coVerify { repository.getEventDetails(emptyEventId) }
    }
}