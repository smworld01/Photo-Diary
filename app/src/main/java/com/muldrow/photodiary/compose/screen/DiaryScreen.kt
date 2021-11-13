package com.muldrow.photodiary.compose.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muldrow.photodiary.viewmodel.DiaryViewModel
import com.muldrow.photodiary.compose.*

@Composable
fun DiaryScreen(uid: Int?, model: DiaryViewModel = viewModel()) {
    val diary = model.getDiary(uid)
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                AppBar(title = diary?.title?: "404")
            }
        ) {
            Text(text = "${diary?.date?.time}")
        }
    }
}