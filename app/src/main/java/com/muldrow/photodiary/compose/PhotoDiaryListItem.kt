package com.muldrow.photodiary.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muldrow.photodiary.room.entities.PhotoDiary
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun PhotoDiaryListItem(photoDiary: PhotoDiary, showDiary: (Int) -> Unit ) {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                showDiary(photoDiary.id)
            },
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = sdf.format(photoDiary.date), style = MaterialTheme.typography.h6)
                Text(text = photoDiary.title, style = MaterialTheme.typography.h6)
            }
        }
    }
}