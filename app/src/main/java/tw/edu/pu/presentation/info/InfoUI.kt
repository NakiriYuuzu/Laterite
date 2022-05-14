package tw.edu.pu.presentation.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tw.edu.pu.presentation.theme.ui.LateriteTheme
import tw.edu.pu.R
import tw.edu.pu.presentation.other.header.Header

@Composable
fun InfoScreen(navController: NavController?) {
    Scaffold {
        Header()
        Column(modifier = Modifier.padding(top = 110.dp)) {
            InfoShowcase(
                painter = painterResource(id = R.drawable.redsand),
                contentDescription = "Art Description",
                title = "藝術節介紹",
                info = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Enim dignissim at semper.",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(LateriteTheme.colors.background)
            )
        }
    }
}

@Composable
fun InfoShowcase(
    painter: Painter,
    contentDescription: String,
    title: String,
    info: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LateriteTheme.colors.background)
    ) {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Image(
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                )
            }

            Text(
                text = title,
                textAlign = TextAlign.Left,
                color = LateriteTheme.colors.primaryText,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(20.dp, 15.dp, 20.dp, 15.dp)
            )

            Text(
                text = info,
                textAlign = TextAlign.Left,
                color = LateriteTheme.colors.primaryText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(20.dp, 15.dp, 20.dp, 15.dp)
            )
        }
    }
}


@Composable
@Preview
fun InfoScreenPreview() {
    InfoScreen(null)
}