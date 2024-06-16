@file:OptIn(ExperimentalComposeUiApi::class)

package com.cvelez.challengeyape.ui.home
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cvelez.challengeyape.R
import com.cvelez.challengeyape.domain.model.Recipes
import com.cvelez.challengeyape.ui.home.components.RecipeItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    onItemClicked: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    darkMode: MutableState<Boolean>
) {

    val state = viewModel.state
    val eventFlow = viewModel.eventFlow
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is HomeViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopBar(darkMode = darkMode)
        },
        bottomBar = {
            HomeBottomBar(
                showPrevious = state.showPrevious,
                showNext = state.showNext,
                onPreviousPressed = { viewModel.getListRecipe(false) },
                onNextPressed = { viewModel.getListRecipe(true) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .background(Color.Transparent),
        ) {
            HomeContent(
                modifier = Modifier.padding(),
                input = state.input,
                onItemClicked = { onItemClicked(it) },
                isLoading = state.isLoading,
                characters = state.recipes,
                page = state.page,
                getCharacters = { input, press -> viewModel.searchRecipes(input,press) },
                onEvent = { viewModel.onEvent(it) }
            )
        }
    }
}

@Composable
private fun HomeTopBar(
    modifier: Modifier = Modifier,
    darkMode: MutableState<Boolean>
) {
    TopAppBar(
        actions = {
            IconButton(onClick = { darkMode.value = !darkMode.value }) {
                Icon(
                    imageVector = if (darkMode.value) Icons.Default.DarkMode else Icons.Default.LightMode,
                    contentDescription = if (darkMode.value) "Modo Claro" else "Modo Oscuro",
                )
            }
        },
        title = {
            Text(
                text = "Tu receta favorita🥗!",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            )
        }
    )
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    isLoading: Boolean = false,
    input: String = "",
    characters: List<Recipes> = emptyList(),
    getCharacters: (String, Boolean) -> Unit,
    hint: String = "",
    page: Int,
    onEvent: (HomeEvent) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Surface(color = MaterialTheme.colors.background) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.size(20.dp))
            Box(modifier = modifier) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { onEvent(HomeEvent.EnteredRecipe(it)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            getCharacters(input, true) // Realizar nueva búsqueda
                            keyboardController?.hide()
                        },
                    ),
                    singleLine = true,
                    label = { Text("Receta") },
                    placeholder = { Text("Buscar..") },
                    maxLines = 1,
                    shape = RoundedCornerShape(30.dp),
                    trailingIcon = {
                        if (input.isNotBlank()) {
                            IconButton(onClick = {
                                onEvent(HomeEvent.EnteredRecipe(""))
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "Limpiar campo"
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .width(400.dp)
                        .offset(y = (-13).dp)
                        .onFocusChanged {
                            isHintDisplayed = (!it.isFocused) && input.isNotEmpty()
                        }
                )
                if (isHintDisplayed) {
                    Text(
                        text = hint,
                        color = Color.LightGray,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                    )
                }
            }
        }
    }

    // Utilizamos un efecto lanzado para asegurar que se recargue la lista cuando cambie el input
    LaunchedEffect(input) {
        // Si input cambia a vacío, volvemos a obtener las recetas
        if (input.isEmpty()) {
            getCharacters("", true) // Realizar nueva búsqueda
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 8.dp)
        ) {
            items(characters.size) { index ->
                RecipeItem(
                    modifier = Modifier.padding(4.dp),
                    item = characters[index],
                    onItemClicked = onItemClicked
                )
                // Verificar si estamos cerca del final de la lista para cargar más
                if (index == characters.size - 1 && !isLoading) {
                    getCharacters(input, false) // Cargar más resultados
                }
            }
        }
        if (isLoading) FullScreenLoading()
    }
}


@Composable
private fun HomeBottomBar(
    showPrevious: Boolean,
    showNext: Boolean,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showPrevious,
                onClick = onPreviousPressed
            ) {
                Text(text = stringResource(id = R.string.previous_button))
            }
            TextButton(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                enabled = showNext,
                onClick = onNextPressed
            ) {
                Text(text = stringResource(id = R.string.next_button))
            }
        }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}