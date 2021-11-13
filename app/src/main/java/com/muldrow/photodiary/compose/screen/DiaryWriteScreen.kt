package com.muldrow.photodiary.compose.screen

import android.net.Uri
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muldrow.photodiary.viewmodel.DiaryViewModel
import com.muldrow.photodiary.compose.*
import com.muldrow.photodiary.data.PhotoDiary
import java.util.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun DiaryWriteScreen(model: DiaryViewModel = viewModel()) {
    var title by rememberSaveable { mutableStateOf("") }
    val setTitle = { s:String -> title = s }
    val diary = remember { PhotoDiary(0, null, Date(), title) }

    var uri: Uri? by rememberSaveable { mutableStateOf(null)}
    var setUri = { u:Uri? -> uri = u}


    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                EditableAppBar(title = title, setTitle = setTitle)
            }
        ) {
            PhotoDiaryHeader(editable = true, uri, setUri)
        }
    }
}