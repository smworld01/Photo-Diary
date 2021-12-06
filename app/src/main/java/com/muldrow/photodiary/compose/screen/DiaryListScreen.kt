package com.muldrow.photodiary.compose.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.muldrow.photodiary.compose.*
import com.muldrow.photodiary.viewmodel.DiaryViewModel

@Composable
fun DiaryListScreen(
    navController: NavController,
    viewModel: DiaryViewModel
) {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                AppBar(title = "사진 일기")
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate("diary/write") }) {
                    Icon(Icons.Default.Create, contentDescription = "일기 쓰기")
                }
            },
        ) {
            PhotoDiaryList(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}