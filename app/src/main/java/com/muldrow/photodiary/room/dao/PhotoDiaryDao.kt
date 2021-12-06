package com.muldrow.photodiary.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.muldrow.photodiary.room.entities.PhotoDiaryHeader
import com.muldrow.photodiary.room.entities.PhotoDiaryWithContents
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDiaryDao {
    @Insert
    suspend fun write(diaryHeader: PhotoDiaryHeader)

    @Query("SELECT * FROM PhotoDiaryHeader WHERE id == :id")
    suspend fun getTitleById(id: Int): PhotoDiaryHeader

    @Query("SELECT * FROM PhotoDiaryHeader")
    fun getTitleAll(): Flow<List<PhotoDiaryHeader>>

    @Transaction
    @Query("SELECT * FROM PhotoDiaryHeader WHERE id == :id")
    suspend fun getById(id: Int): PhotoDiaryWithContents

    @Transaction
    @Query("SELECT * FROM PhotoDiaryHeader")
    fun getAll(): Flow<List<PhotoDiaryWithContents>>
}