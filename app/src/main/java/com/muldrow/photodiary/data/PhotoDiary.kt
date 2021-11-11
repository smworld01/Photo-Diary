package com.muldrow.photodiary.data

import android.net.Uri
import java.util.*

data class PhotoDiary(
    val uri: Uri?,
    val date: Date,
    val title: String,
)