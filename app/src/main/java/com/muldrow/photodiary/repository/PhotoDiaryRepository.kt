package com.muldrow.photodiary.repository

import com.muldrow.photodiary.room.dao.PhotoDiaryContentDao
import com.muldrow.photodiary.room.dao.PhotoDiaryDao
import com.muldrow.photodiary.room.entities.PhotoDiaryWithContents
import javax.inject.Inject

class PhotoDiaryRepository @Inject constructor(
    private val photoDiaryDao: PhotoDiaryDao,
    private val photoDiaryContentDao: PhotoDiaryContentDao
) {
    fun getPhotoDiaryTitleList() = photoDiaryDao.getTitleAll()

    suspend fun getPhotoDiaryById(id: Int) = photoDiaryDao.getById(id = id)

    suspend fun writePhotoDiary(diary: PhotoDiaryWithContents) {
        val titleId = photoDiaryDao.write(diaryHeader = diary.header)

        photoDiaryContentDao.write(
            diary = diary.bodies.map { it.copy(titleId = titleId) }
        )
    }
}