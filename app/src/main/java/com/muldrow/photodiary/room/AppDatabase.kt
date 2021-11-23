package com.muldrow.photodiary.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.muldrow.photodiary.room.dao.PhotoDiaryContentDao
import com.muldrow.photodiary.room.dao.PhotoDiaryDao
import com.muldrow.photodiary.room.entities.PhotoDiary
import com.muldrow.photodiary.room.entities.PhotoDiaryContent

@Database(entities = [PhotoDiary::class, PhotoDiaryContent::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDiaryDao(): PhotoDiaryDao
    abstract fun photoDiaryContentDao(): PhotoDiaryContentDao
}