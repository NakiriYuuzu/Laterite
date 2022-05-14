package tw.edu.pu.presentation.other.alert

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import tw.edu.pu.presentation.theme.ui.LateriteTheme

/**
 * You need to create val [isDialogOpen] = remember { [mutableStateOf(false)] }
 */
@Composable
fun CallAlertDialog(
    isDialogOpen: MutableState<Boolean>,
    title: String,
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit
) {
//     val isDialogOpen = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
    ) {
        AlertDialogUI(
            isDialogOpen,
            title = title,
            onPositiveClick = onPositiveClick,
            onNegativeClick = onNegativeClick
        )
    }
}

@Composable
fun AlertDialogUI(
    dialogOpen: MutableState<Boolean>,
    title: String,
    onPositiveClick:() -> Unit,
    onNegativeClick:() -> Unit
) {
    if (dialogOpen.value) {
        Dialog(onDismissRequest = { dialogOpen.value = false }) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                shape = RoundedCornerShape(20.dp),
                color = LateriteTheme.colors.background
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.padding(20.dp))

                    Text(
                        text = title,
                        color = LateriteTheme.colors.primaryText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 10.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        IconButton(
                            onClick = onPositiveClick
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Circle,
                                contentDescription = "true",
                                tint = LateriteTheme.colors.primaryText
                            )
                        }

                        Spacer(modifier = Modifier.width(55.dp))

                        IconButton(
                            onClick = onNegativeClick
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = "false",
                                tint = LateriteTheme.colors.primaryText
                            )
                        }
                    }
                }
            }
        }
    }
}