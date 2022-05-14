package tw.edu.pu.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import tw.edu.pu.R
import tw.edu.pu.presentation.other.header.HeaderWithBackSpace
import tw.edu.pu.presentation.other.imagecard.ImageCard
import tw.edu.pu.presentation.theme.ui.LateriteTheme

@Composable
fun DetailScreen(navController: NavController?, place: String?) {
    Scaffold(modifier = Modifier.background(LateriteTheme.colors.background)) {
        HeaderWithBackSpace(modifier = Modifier.clickable { navController?.popBackStack() })
        Column(modifier = Modifier.padding(top = 110.dp)) {
            HorizontalScroll(place)
        }
    }
}

@Composable
fun HorizontalScroll(place: String?) {
    val information = remember { mutableStateOf("") }
    val paint = remember { mutableStateOf(R.drawable.image) }

    val information2 = remember { mutableStateOf("") }
    val paint2 = remember { mutableStateOf(R.drawable.image) }

    when (place) {
        "1" -> {
            information.value = "01orem ipsum dolor sit amet, consectetur adipiscing elit. Enim dignissim at semper."
            paint.value = R.drawable.art

            information2.value = "01orem ipsum dolor sit amet, consectetur adipiscing elit. Enim dignissim at semper."
            paint2.value = R.drawable.art
        }

        "2" -> {
            information.value = "02orem ipsum dolor sit amet, consectetur adipiscing elit. Enim dignissim at semper."
            paint.value = R.drawable.cofe

            information2.value = "02orem ipsum dolor sit amet, consectetur adipiscing elit. Enim dignissim at semper."
            paint2.value = R.drawable.cofe
        }

        "3" -> {
            information.value = "03orem ipsum dolor sit amet, consectetur adipiscing elit. Enim dignissim at semper."
            paint.value = R.drawable.crist

            information2.value = "03orem ipsum dolor sit amet, consectetur adipiscing elit. Enim dignissim at semper."
            paint2.value = R.drawable.crist
        }
    }

    LazyColumn(modifier = Modifier.background(LateriteTheme.colors.background)) {
        item {
            Column(
                modifier = Modifier.padding(25.dp, 10.dp, 25.dp, 25.dp)
            ) {
                ImageCard(
                    painter = painterResource(id = paint.value),
                    contentDescription = "FieldView",
                    information = information.value,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Column(
                modifier = Modifier.padding(25.dp)
            ) {
                ImageCard(
                    painter = painterResource(id = paint2.value),
                    contentDescription = "FieldView",
                    information = information2.value,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Column(
                modifier = Modifier.padding(3.dp)
            ) {

            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    DetailScreen(navController = null, null)
}