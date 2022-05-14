package tw.edu.pu.presentation.other.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import tw.edu.pu.R
import tw.edu.pu.presentation.theme.ui.LateriteTheme

@Composable
fun Header() {
    Column(
        modifier = Modifier
            .background(color = LateriteTheme.colors.background)
            .padding(vertical = 5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()

        ) {
            Spacer(modifier = Modifier.padding(5.dp))

            Image(painter = painterResource(id = R.drawable.image),
                contentDescription = "Logo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(200.dp, 100.dp)
            )

            Spacer(modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun HeaderWithBackSpace(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = LateriteTheme.colors.background)
            .padding(vertical = 5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()

        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIos,
                contentDescription = "Back",
                tint = LateriteTheme.colors.primaryText,
                modifier = modifier
            )

            Image(painter = painterResource(id = R.drawable.image),
                contentDescription = "Logo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(200.dp, 100.dp)
            )

            Spacer(modifier = Modifier.padding(11.9.dp))
        }
    }
}