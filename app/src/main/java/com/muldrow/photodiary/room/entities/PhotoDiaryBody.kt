package com.muldrow.photodiary.room.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoDiaryBody(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titleId: Long? = null,
    val imageUri: Uri?,
    val text: String,
)
