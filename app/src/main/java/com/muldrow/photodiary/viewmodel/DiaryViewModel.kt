package com.muldrow.photodiary.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.muldrow.photodiary.PhotoDiaryProvider
import com.muldrow.photodiary.data.PhotoDiary

// TODO Hilt
class DiaryViewModel(
    private val saveState: SavedStateHandle,
    // TODO Repository
): ViewModel() {

    fun getDiary(uid: Int?): PhotoDiary? {
        return PhotoDiaryProvider.diaryList.find { photoDiary ->
            photoDiary.uid == uid
        }
    }

    fun addDiary() {
        // TODO
    }
}