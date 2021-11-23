package com.muldrow.photodiary.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.muldrow.photodiary.room.entities.PhotoDiary
import com.muldrow.photodiary.room.entities.PhotoDiaryWithContents
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDiaryDao {
    @Insert
    suspend fun write(diary: PhotoDiary)

    @Query("SELECT * FROM PhotoDiary WHERE id == :id")
    suspend fun getTitleById(id: Int): PhotoDiary

    @Query("SELECT * FROM PhotoDiary")
    fun getTitleAll(): Flow<List<PhotoDiary>>

    @Transaction
    @Query("SELECT * FROM PhotoDiary WHERE id == :id")
    suspend fun getById(id: Int): PhotoDiaryWithContents

    @Transaction
    @Query("SELECT * FROM PhotoDiary")
    fun getAll(): Flow<List<PhotoDiaryWithContents>>
}