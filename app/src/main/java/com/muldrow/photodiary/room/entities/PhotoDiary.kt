package com.muldrow.photodiary.room.entities

import android.net.Uri
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PhotoDiary(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val imageUri: Uri?,
    val date: Date
)
