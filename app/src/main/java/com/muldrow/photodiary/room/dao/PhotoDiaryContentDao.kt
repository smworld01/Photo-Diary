package com.muldrow.photodiary.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.muldrow.photodiary.room.entities.PhotoDiary
import com.muldrow.photodiary.room.entities.PhotoDiaryContent
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDiaryContentDao {
    @Insert
    suspend fun write(diary: PhotoDiaryContent)

    @Query("SELECT * FROM PhotoDiaryContent WHERE id == :id")
    suspend fun getById(id: Int): PhotoDiaryContent


    @Query("SELECT * FROM PhotoDiaryContent WHERE titleId == :titleId")
    suspend fun getByTitleId(titleId: Int): PhotoDiaryContent

    @Query("SELECT * FROM PhotoDiaryContent")
    fun getAll(): Flow<List<PhotoDiaryContent>>
}