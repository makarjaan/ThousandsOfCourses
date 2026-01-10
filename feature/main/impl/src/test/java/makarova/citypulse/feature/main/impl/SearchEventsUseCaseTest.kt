package makarova.citypulse.feature.main.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.main.api.model.SearchResultModel
import makarova.citypulse.feature.main.api.repository.EventsRepository
import makarova.citypulse.feature.main.impl.usecasae.SearchEventsUseCaseImpl
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchEventsUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: EventsRepository = mockk()

    private lateinit var useCase: SearchEventsUseCaseImpl

    @Before
    fun setup() {
        useCase = SearchEventsUseCaseImpl(repository, testDispatcher)
    }

    @Test
    fun `returns search results`() = testScope.runTest {
        val result = listOf(
            SearchResultModel(
                id = "1",
                title = "Concert",
                city = "Москва",
                category = "music",
                address = "a",
                imageUrl = "aa",
                isFree = false,
                favoritesCount = 7,
                description = "",
                contentType = "",
                dates = "",
                siteUrl = "",
                tags = listOf()
            )
        )

        coEvery {
            repository.searchEvents(
                query = "music",
                location = "Москва",
                contentType = null,
                page = 1,
                pageSize = 20,
                isFree = true
            )
        } returns result

        val actual = useCase(
            query = "music",
            location = "Москва",
            contentType = null,
            page = 1,
            pageSize = 20,
            isFree = true
        )

        assertEquals(result, actual)
        coVerify(exactly = 1) { repository.searchEvents(any(), any(), any(), any(), any(), any()) }
    }
}
