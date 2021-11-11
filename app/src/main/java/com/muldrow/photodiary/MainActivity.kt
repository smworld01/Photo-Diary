package com.muldrow.photodiary

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muldrow.photodiary.data.PhotoDiary
import com.muldrow.photodiary.ui.theme.PhotoDiaryTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoDiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                                 AppBar(title = "hello")
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Filled.Add, contentDescription = "글쓰기")
                            }
                        }
                    ) {
                        PhotoDiaryList()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotoDiaryTheme {
        Column {
            AppBar("hello")
            PhotoDiaryList()
        }
    }
}

@Composable
fun AppBar(title: String) {
    TopAppBar {
        Row {
            Text(text = title,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PhotoDiaryList() {
    val photoDiaries = remember {PhotoDiaryProvider.diaryList}
    LazyColumn {
        items(
            items = photoDiaries,
            itemContent = {
                PhotoDiaryListItem(photoDiary = it)
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotoDiaryListItem(photoDiary: PhotoDiary) {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = {
            Log.e("test","click")
        }
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = sdf.format(photoDiary.date), style = typography.h6)
                Text(text = photoDiary.title, style = typography.h6)
            }
        }
    }
}

