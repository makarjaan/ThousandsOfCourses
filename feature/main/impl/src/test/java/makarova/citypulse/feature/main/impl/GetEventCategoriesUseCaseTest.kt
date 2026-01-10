package makarova.citypulse.feature.main.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.main.api.model.EventCategoryModel
import makarova.citypulse.feature.main.api.repository.CategoriesRepository
import makarova.citypulse.feature.main.impl.usecasae.GetEventCategoriesUseCaseImpl
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetEventCategoriesUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: CategoriesRepository = mockk()

    private lateinit var useCase: GetEventCategoriesUseCaseImpl

    @Before
    fun setup() {
        useCase = GetEventCategoriesUseCaseImpl(repository, testDispatcher)
    }

    @Test
    fun `returns event categories`() = testScope.runTest {
        val categories = listOf(
            EventCategoryModel(slug = "music", name = "Музыка"),
            EventCategoryModel(slug = "theatre", name = "Театр")
        )

        coEvery { repository.getCategories() } returns categories

        val result = useCase()

        assertEquals(categories, result)
        coVerify(exactly = 1) { repository.getCategories() }
    }
}
