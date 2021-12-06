package com.muldrow.photodiary.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.muldrow.photodiary.viewmodel.DiaryViewModel

@Composable
fun PhotoDiaryList(
    navController: NavController,
    viewModel: DiaryViewModel,
) {
    val photoDiaries by viewModel.photoDiaryList.observeAsState()
    val showDiary = { uid: Int -> navController.navigate("diary/item/$uid") }
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Transparent.copy(0.1f)),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = photoDiaries?: emptyList(),
            itemContent = {
                PhotoDiaryListItem(photoDiaryHeader = it, showDiary = showDiary)
            }
        )
    }

    if (photoDiaries.isNullOrEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "등록된 일기가 없어요")
        }
    }
}