package com.muldrow.photodiary.room.entities

import android.net.Uri
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*

@Entity
data class PhotoDiaryWithContents(
    @Embedded val header: PhotoDiary? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "titleId",
    )
    val contents: List<PhotoDiaryContent>? = null
)
