package com.cvelez.challengeyape.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.cvelez.challengeyape.R.*
import com.cvelez.challengeyape.domain.model.Recipe
import com.cvelez.challengeyape.ui.Screen
import com.cvelez.challengeyape.ui.detail.components.DetailProperty
import com.cvelez.challengeyape.ui.detail.components.RecipeImage
import com.cvelez.challengeyape.ui.detail.components.mirroringBackIcon

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    upPress: () -> Unit,
    navController: NavController
) {
    val state = viewModel.state
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        DetailContent(
            recipe = state.recipe,
            upPress = upPress,
            navController = navController
        )
    }
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    recipe: Recipe?,
    upPress: () -> Unit,
    navController: NavController
) {
    Box(modifier.fillMaxSize()) {
        Column {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                recipe = recipe
            )
            Body(recipe = recipe, navController = navController)
        }
        Up(upPress)
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    recipe: Recipe?,
) {
    var scale by remember { mutableStateOf(1f) }

    Box(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    scale = (scale * zoom).coerceIn(1f, 3f)
                }
            }
    ) {
        RecipeImage(image = recipe?.image, scalex = scale, scaley = scale)
    }
}

@SuppressLint("QueryPermissionsNeeded")
@Composable
private fun Body(recipe: Recipe?, navController: NavController = rememberNavController()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            textAlign = TextAlign.Center,
            text = recipe?.name ?: "",
            fontFamily = FontFamily.Serif,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.h4.copy(fontSize = 30.sp),
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                DetailProperty(label = stringResource(string.name), value = recipe?.name, imageVector = Icons.Filled.Dining)
                DetailProperty(label = stringResource(string.description), value = recipe?.description, imageVector = Icons.Outlined.AutoStories)
                DetailProperty(label = stringResource(string.location), value = recipe?.location?.latitude, imageVector = Icons.Outlined.LocationOn)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                onClick = {
                    val latitude = recipe?.location?.latitude ?: "0.0"
                    val longitude = recipe?.location?.longitude ?: "0.0"
                    navController.navigate(Screen.Map.createRoute(latitude = latitude.toDouble(), longitude = longitude.toDouble()))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.textButtonColors(backgroundColor = MaterialTheme.colors.primary)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Icono de ubicación",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Ver origen de la receta",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .size(36.dp)
    ) {
        Icon(
            imageVector = mirroringBackIcon(),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = null
        )
    }
}
