package com.muldrow.photodiary.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EditableAppBar(
    title: String,
    setTitle: (String) -> Unit,
    colors: TextFieldColors,
    onDone: (KeyboardActionScope.() -> Unit)? = null,
) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    ) {
        Row {
            TextField(value = title, onValueChange = setTitle,
                modifier = Modifier.fillMaxSize(),
                textStyle = MaterialTheme.typography.subtitle1.copy(textAlign = TextAlign.Center),
                singleLine = true,
                colors = colors,
                placeholder = {
                    Text(
                        text = "제목을 입력해 주세요.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                keyboardActions = KeyboardActions(onDone = onDone)
            )
        }
    }
}