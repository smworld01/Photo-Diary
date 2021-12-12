package com.muldrow.photodiary.compose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muldrow.photodiary.viewmodel.DiaryViewModel
import com.muldrow.photodiary.compose.*
import com.muldrow.photodiary.compose.produce.getBitmapPalette
import com.muldrow.photodiary.compose.produce.loadPhotoDiary
import com.muldrow.photodiary.room.entities.PhotoDiaryWithContents
import com.muldrow.photodiary.ui.theme.PhotoDiaryTheme

@Composable
fun DiaryScreen(
    uid: Int?,
    model: DiaryViewModel = viewModel()
) {
    PhotoDiaryTheme {
        val scope = rememberCoroutineScope()
        val diary by loadPhotoDiary(uid = uid, viewModel = model)

        val diaryBody = diary?.bodies?.get(0)
        val palette by getBitmapPalette(diaryBody?.imageUri)

        Scaffold(
            backgroundColor = palette?.vibrantSwatch?.rgb?.let { Color(it) }?: MaterialTheme.colors.background,
            topBar = {
                AppBar(title = diary?.header?.title?: "404")
            }
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                PhotoDiaryContent(imgUri = diaryBody?.imageUri)

                Text(text = diaryBody?.text?: "")
            }
        }
    }
}