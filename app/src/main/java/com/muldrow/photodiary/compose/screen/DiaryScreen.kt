package com.muldrow.photodiary.compose.screen

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muldrow.photodiary.viewmodel.DiaryViewModel
import com.muldrow.photodiary.compose.*

@Composable
fun DiaryScreen(uid: Int?, model: DiaryViewModel = viewModel()) {
    Surface(color = MaterialTheme.colors.background) {
        val composableScope = rememberCoroutineScope()
        val diary by model.photoDiary.observeAsState()

        Scaffold(
            topBar = {
                AppBar(title = diary?.header?.title?: "404")
            }
        ) {
            Text(text = "${diary?.header?.date?.time}")
        }
    }
}