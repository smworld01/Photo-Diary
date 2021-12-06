package com.muldrow.photodiary.room.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class PhotoDiaryWithContents(
    @Embedded val header: PhotoDiaryHeader? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "titleId",
    )
    val bodies: List<PhotoDiaryBody>? = null
)
