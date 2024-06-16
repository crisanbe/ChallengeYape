package com.cvelez.challengeyape.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.cvelez.challengeyape.R
import com.cvelez.challengeyape.domain.model.Recipes

@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    item: Recipes,
    onItemClicked: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier
            .clickable { onItemClicked(item.id) }
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.purple_200))
    ) {
        Column(modifier = Modifier.padding(2.dp)) {
            Image(
                painter = rememberImagePainter(data = item.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) { index ->
                    val isFilled = index < 5 // Aquí se puede poner lógica para llenar parcialmente
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (isFilled) Color.Yellow else Color.Transparent,
                        modifier = Modifier.size(24.dp)
                            .padding(end = 4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(color = Color.Black, shape = RoundedCornerShape(4.dp))
                    )
                }
                Text(
                    text = "5.0", // Cambiado a 5.0
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
