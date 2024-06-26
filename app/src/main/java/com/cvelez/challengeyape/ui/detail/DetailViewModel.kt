package com.cvelez.challengeyape.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cvelez.challengeyape.data.Result
import com.cvelez.challengeyape.domain.use_case.GetDetailRecipeUseCase
import com.cvelez.challengeyape.util.ID_PARAMETER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetDetailRecipeUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(DetailState())
        private set

    init {
        getCharacter()
    }

    fun getCharacter() {
        savedStateHandle.get<Int>(ID_PARAMETER)?.let { characterId ->
            viewModelScope.launch {
                getCharacterUseCase(characterId).also { result ->
                    when (result) {
                        is Result.Success -> {
                            state = state.copy(
                                recipe = result.data,
                                isLoading = false
                            )
                        }
                        is Result.Error -> {
                            state = state.copy(
                                isLoading = false
                            )
                        }
                        is Result.Loading -> {
                            state = state.copy(
                                isLoading = true
                            )
                        }
                    }
                }
            }
        }
    }
}