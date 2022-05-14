package tw.edu.pu.presentation.other.imagecard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.presentation.theme.ui.LateriteTheme

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    field: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = 4.dp
    ) {
        Box(modifier = Modifier.height(175.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = modifier
            )
        }
    }

    Text(
        field,
        style = TextStyle(
            color = LateriteTheme.colors.primaryText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(top = 10.dp)
    )

    Text(
        time,
        style = TextStyle(
            color = LateriteTheme.colors.primaryText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    information: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp
    ) {
        Box(modifier = Modifier.height(175.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = modifier
            )
        }
    }

    Text(
        information,
        style = TextStyle(
            color = LateriteTheme.colors.primaryText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        modifier = Modifier.padding(top = 10.dp)
    )
}