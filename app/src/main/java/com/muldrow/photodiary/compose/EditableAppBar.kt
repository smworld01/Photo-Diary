package com.muldrow.photodiary.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun EditableAppBar(title: String, setTitle: (String) -> Unit) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    ) {
        Row {
            TextField(value = title, onValueChange = setTitle,
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.h6.copy(textAlign = TextAlign.Center),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Unspecified),
                placeholder = {
                    Text(
                        text = "제목을 입력해 봐요.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )}
            )
        }
    }
}