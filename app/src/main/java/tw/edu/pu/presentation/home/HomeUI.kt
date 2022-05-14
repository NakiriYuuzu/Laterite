package tw.edu.pu.presentation.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import tw.edu.pu.presentation.theme.ui.LateriteTheme
import tw.edu.pu.R
import tw.edu.pu.presentation.other.alert.CallAlertDialog
import tw.edu.pu.presentation.other.header.Header
import tw.edu.pu.presentation.other.imagecard.ImageCard
import tw.edu.pu.presentation.other.navigation.NavigationData

@Composable
fun HomeScreen(navController: NavController?, context: ComponentActivity?) {
    Scaffold {
        Header()
        Column(modifier = Modifier.padding(top = 110.dp)) {
            VerticalHorizontalScroll(navController, context = context)
        }
    }
}

@Composable
fun VerticalHorizontalScroll(navController: NavController?, context: ComponentActivity?) {
    // 1 = 藝術中心, 2 = 主顧咖啡, 3 = 主顧聖母堂
    var place by remember { mutableStateOf("") }
    var detail by remember { mutableStateOf(0) }

    LazyColumn(modifier = Modifier.background(LateriteTheme.colors.background)) {
        item {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(100.dp)
//                    .padding(10.dp, 10.dp, 10.dp, 10.dp)
//            ) {
//            }

            // Modifier Alert Here
            val isDialogOpen = remember { mutableStateOf(false) }
            val title = remember { mutableStateOf("") }
            // End Alert Modifier

            Column(
                modifier = Modifier.padding(25.dp, 10.dp, 25.dp, 10.dp)
            ) {
                ImageCard(
                    painter = painterResource(id = R.drawable.art),
                    contentDescription = "FieldView",
                    field = "藝術中心",
                    time = "時間: 2022/05/19",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            title.value = "需要導覽到藝術中心嗎？"
                            place = "24.226763866991696,120.5799739446736"
                            detail = 1
                            isDialogOpen.value = true
                        }
                )
            }

            Column(
                modifier = Modifier.padding(25.dp, 10.dp, 25.dp, 10.dp)
            ) {
                ImageCard(
                    painter = painterResource(id = R.drawable.cofe),
                    contentDescription = "FieldView",
                    field = "主顧咖啡二館",
                    time = "時間: 2022/05/19",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            title.value = "需要導覽到主顧咖啡二館嗎？"
                            place = "24.228481294667887, 120.58151889695755"
                            detail = 2
                            isDialogOpen.value = true
                        }
                )
            }

            Column(
                modifier = Modifier.padding(25.dp, 10.dp, 25.dp, 10.dp)
            ) {
                ImageCard(
                    painter = painterResource(id = R.drawable.crist),
                    contentDescription = "FieldView",
                    field = "主顧聖母堂",
                    time = "時間: 2022/05/19",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            title.value = "需要導覽到主顧聖母堂嗎？"
                            place = "24.22811893430123,120.5814384306243"
                            detail = 3
                            isDialogOpen.value = true
                        }
                )
            }

            CallAlertDialog(
                isDialogOpen = isDialogOpen,
                title = title.value,
                onPositiveClick = {
                    // navController?.navigate(NavigationData.Direction.route + "/$place")
                    // context?.startActivity(Intent(context, MapActivity::class.java))

                    val gmmIntentUri = Uri.parse("google.streetview:cbll=$place")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context?.startActivity(mapIntent)

                    isDialogOpen.value = false
                },
                onNegativeClick = {
                    navController?.navigate(NavigationData.Detail.route + "/$detail")
                    isDialogOpen.value = false
                }
            )
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(null, null)
}