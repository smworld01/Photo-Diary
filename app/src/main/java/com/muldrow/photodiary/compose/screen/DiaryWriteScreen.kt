package com.muldrow.photodiary.compose.screen

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import com.muldrow.photodiary.viewmodel.DiaryViewModel
import com.muldrow.photodiary.compose.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.muldrow.photodiary.compose.produce.getBitmapPalette
import com.muldrow.photodiary.room.entities.PhotoDiaryBody
import com.muldrow.photodiary.room.entities.PhotoDiaryHeader
import com.muldrow.photodiary.room.entities.PhotoDiaryWithContents
import com.muldrow.photodiary.ui.theme.PhotoDiaryTheme
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DiaryWriteScreen(
    navController: NavController,
    viewModel: DiaryViewModel
) {

    val (title, setTitle) = rememberSaveable { mutableStateOf("") }

    val (uri, setUri) = rememberSaveable { mutableStateOf<Uri?>(null)}

    val (text, setText) = rememberSaveable { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val contentState = remember { MutableTransitionState(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val palette by getBitmapPalette(uri)

    val write = {
        scope.launch {
            viewModel.writeDiary(
                PhotoDiaryWithContents(
                    header = PhotoDiaryHeader(title = title, date = Date()),
                    bodies = listOf( PhotoDiaryBody(imageUri = uri, text = text) )
                )
            )
        }
    }
    PhotoDiaryTheme {
        Scaffold(
            backgroundColor = palette?.vibrantSwatch?.rgb?.let { Color(it) }?: MaterialTheme.colors.background,
            topBar = {
                Box {
                    PhotoDiaryTopBar(
                        title = title,
                        setTitle = setTitle,
                        onDone = {
                            if (!contentState.currentState) contentState.targetState = true
                            keyboardController?.hide()
                        }
                    )

                    if (uri != null || text.isNotBlank()) {
                        IconButton(
                            onClick = {
                                write()
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(horizontal = 8.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Done, contentDescription = "완료")
                        }
                    }
                }
            },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                AnimatedVisibility(
                    visibleState = contentState,
                    enter = fadeIn(),
                    modifier = Modifier.weight(1f, fill = false)
                ) {
                    Crossfade(targetState = uri, modifier = Modifier.animateContentSize()) {
                        PhotoDiaryContent(
                            editable = true, imgUri = it, setImgUri = setUri
                        )
                    }
                }
                AnimatedVisibility(
                    visible = contentState.isIdle && contentState.currentState,
                    modifier = Modifier.weight(1f)
                ) {
                    PhotoDiaryText(
                        text = text,
                        onTextChange = setText,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}