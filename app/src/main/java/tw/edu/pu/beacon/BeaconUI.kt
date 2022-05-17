package tw.edu.pu.beacon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import tw.edu.pu.presentation.theme.ui.LateriteTheme

@Composable
fun BeaconScreen(
    isPopUp: MutableState<Boolean>,
    title: String,
    image: Int,
    isPlayClick: Boolean,
    onPlayButton:() -> Unit,
    onCloseButton:() -> Unit
) {
    if (isPopUp.value) {
        Dialog(onDismissRequest = { isPopUp.value = false }) {
            Surface(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = LateriteTheme.colors.background
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        IconButton(onClick = onCloseButton) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                modifier = Modifier
                                    .size(40.dp, 40.dp),
                                tint = LateriteTheme.colors.primaryText
                            )
                        }

                        Spacer(modifier = Modifier.padding(5.dp))
                    }

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(20.dp),
                            shape = RoundedCornerShape(20.dp),
                            elevation = 4.dp
                        ) {
                            Box(
                                modifier = Modifier
                            ) {
                                Image(
                                    painter = painterResource(id = image),
                                    contentDescription = "Image",
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }

                        Text(
                            text = title,
                            modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp),
                            fontSize = 16.sp,
                            color = LateriteTheme.colors.primaryText
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 0.dp, 20.dp)
                    ) {
                        Row {
                            IconButton(onClick = onPlayButton) {
                                Icon(
                                    imageVector = if(isPlayClick) Icons.Default.PlayCircle else Icons.Default.PauseCircle,
                                    contentDescription = "Play",
                                    tint = LateriteTheme.colors.primaryText,
                                    modifier = Modifier
                                        .size(50.dp, 50.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}