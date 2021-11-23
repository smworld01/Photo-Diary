package com.muldrow.photodiary.room

import android.content.Context
import androidx.room.Room
import com.muldrow.photodiary.room.dao.PhotoDiaryContentDao
import com.muldrow.photodiary.room.dao.PhotoDiaryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "SQLite"
        ).build()
    }

    @Provides
    fun providePhotoDiaryDao(appDatabase: AppDatabase): PhotoDiaryDao {
        return appDatabase.photoDiaryDao()
    }

    @Provides
    fun providePhotoDiaryContentsDao(appDatabase: AppDatabase): PhotoDiaryContentDao {
        return appDatabase.photoDiaryContentDao()
    }
}