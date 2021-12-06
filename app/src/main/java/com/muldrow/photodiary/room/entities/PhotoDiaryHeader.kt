package com.muldrow.photodiary.room.entities

import android.net.Uri
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class PhotoDiaryHeader(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val date: Date
)
