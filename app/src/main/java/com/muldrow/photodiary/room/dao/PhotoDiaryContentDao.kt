package com.muldrow.photodiary.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.muldrow.photodiary.room.entities.PhotoDiaryBody
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDiaryContentDao {
    @Insert
    suspend fun write(diary: PhotoDiaryBody)

    @Query("SELECT * FROM PhotoDiaryBody WHERE id == :id")
    suspend fun getById(id: Int): PhotoDiaryBody


    @Query("SELECT * FROM PhotoDiaryBody WHERE titleId == :titleId")
    suspend fun getByTitleId(titleId: Int): PhotoDiaryBody

    @Query("SELECT * FROM PhotoDiaryBody")
    fun getAll(): Flow<List<PhotoDiaryBody>>
}