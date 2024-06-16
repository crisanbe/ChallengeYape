import androidx.lifecycle.SavedStateHandle
import com.cvelez.challengeyape.domain.model.Recipes
import com.cvelez.challengeyape.domain.use_case.GetDetailRecipeUseCase
import com.cvelez.challengeyape.ui.detail.DetailViewModel
import com.cvelez.challengeyape.util.ID_PARAMETER
import com.cvelez.challengeyape.data.Result
import com.cvelez.challengeyape.data.source.remote.dto.Location
import com.cvelez.challengeyape.domain.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val getCharacterUseCase: GetDetailRecipeUseCase = mock()
    private val savedStateHandle: SavedStateHandle = mock()
    private val testDispatcher = UnconfinedTestDispatcher()
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = DetailViewModel(getCharacterUseCase, savedStateHandle)
    }

    @Test
    fun `when getCharacter is called and result is Success, state is updated correctly`() = runBlockingTest {
        val recipe = Recipe(id = 1, name = "Test", location = Location("Test", "Test"), image = "Test",description = "Test")
        whenever(getCharacterUseCase(1)).thenReturn(Result.Success(recipe))
        whenever(savedStateHandle.get<Int>(ID_PARAMETER)).thenReturn(1)

        viewModel.getCharacter()

        assertEquals(recipe, viewModel.state.recipe)
        assertEquals(false, viewModel.state.isLoading)
    }

    @Test
    fun `when getCharacter is called and result is Error, state is updated correctly`() = runBlockingTest {
        whenever(getCharacterUseCase(1)).thenReturn(Result.Error("Error"))
        whenever(savedStateHandle.get<Int>(ID_PARAMETER)).thenReturn(1)

        viewModel.getCharacter()

        assertEquals(null, viewModel.state.recipe)
        assertEquals(false, viewModel.state.isLoading)
    }

    @Test
    fun `when getCharacter is called and result is Loading, state is updated correctly`() = runBlockingTest {
        whenever(getCharacterUseCase(1)).thenReturn(Result.Loading())
        whenever(savedStateHandle.get<Int>(ID_PARAMETER)).thenReturn(1)

        viewModel.getCharacter()

        assertEquals(null, viewModel.state.recipe)
        assertEquals(true, viewModel.state.isLoading)
    }
}