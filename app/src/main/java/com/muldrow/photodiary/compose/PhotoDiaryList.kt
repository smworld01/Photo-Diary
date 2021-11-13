package com.muldrow.photodiary.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.muldrow.photodiary.PhotoDiaryProvider

@Composable
fun PhotoDiaryList(navController: NavController) {
    val photoDiaries = remember { PhotoDiaryProvider.diaryList}
    val showDiary = { uid: Int -> navController.navigate("diary/item/$uid") }
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Transparent.copy(0.1f)),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = photoDiaries,
            itemContent = {
                PhotoDiaryListItem(photoDiary = it, showDiary = showDiary)
            }
        )
    }
}