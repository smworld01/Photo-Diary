package com.muldrow.photodiary.compose

import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PhotoDiaryTopBar(
    title: String,
    setTitle: (String) -> Unit,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Unspecified,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent
    ),
    onDone: (KeyboardActionScope.() -> Unit)? = null
) {
    EditableAppBar(title = title, setTitle = setTitle, colors = colors, onDone = onDone)
}