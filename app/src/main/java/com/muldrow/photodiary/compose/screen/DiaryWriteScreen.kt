package com.muldrow.photodiary.compose.screen

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.muldrow.photodiary.viewmodel.DiaryViewModel
import com.muldrow.photodiary.compose.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.muldrow.photodiary.room.entities.PhotoDiaryWithContents

@Composable
fun DiaryWriteScreen(viewModel: DiaryViewModel) {
    var title by rememberSaveable { mutableStateOf("") }
    val setTitle = { s:String -> title = s }
    val diary = remember { PhotoDiaryWithContents() }

    var uri: Uri? by rememberSaveable { mutableStateOf(null)}
    val setUri = { u:Uri? -> if(u != null) uri = u}
    val delUri = { uri = null }

    var text by rememberSaveable { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                PhotoDiaryHeader(title = title, setTitle = setTitle)
            }
        ) {
            ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                Column(modifier = Modifier.verticalScroll(scrollState)) {
                    PhotoDiaryBody(editable = true, imgUri = uri, registerImgUri = setUri)
                    PhotoDiaryText(text = text, onTextChange = { text = it}, modifier = Modifier.navigationBarsPadding() )
                }
            }
        }
    }
}