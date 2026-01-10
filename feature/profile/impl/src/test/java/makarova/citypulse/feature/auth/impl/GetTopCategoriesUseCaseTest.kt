package makarova.citypulse.feature.auth.impl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import makarova.citypulse.feature.main.api.repository.CategoriesRepository
import makarova.citypulse.feature.profile.impl.usecase.GetTopCategoriesUseCaseImpl
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetTopCategoriesUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val repository: CategoriesRepository = mockk()

    private lateinit var useCase: GetTopCategoriesUseCaseImpl

    @Before
    fun setup() {
        useCase = GetTopCategoriesUseCaseImpl(
            repository = repository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `returns top categories for user`() = testScope.runTest {
        val email = "test@mail.com"
        val categories = listOf("music", "theatre", "exhibition")

        coEvery { repository.getTopCategories(email) } returns categories

        val result = useCase(email)

        assertEquals(categories, result)
        coVerify(exactly = 1) { repository.getTopCategories(email) }
    }
}
