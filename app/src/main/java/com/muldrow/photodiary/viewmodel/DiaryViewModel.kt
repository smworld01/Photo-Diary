package com.muldrow.photodiary.viewmodel

import androidx.lifecycle.*
import com.muldrow.photodiary.repository.PhotoDiaryRepository
import com.muldrow.photodiary.room.entities.PhotoDiary
import com.muldrow.photodiary.room.entities.PhotoDiaryWithContents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val saveState: SavedStateHandle,
    private val repository: PhotoDiaryRepository
): ViewModel() {
    private val _photoDiary = MutableLiveData<PhotoDiaryWithContents>()

    val photoDiary get(): LiveData<PhotoDiaryWithContents> = _photoDiary
    val photoDiaryList = repository.getPhotoDiaryTitleList().asLiveData()


    suspend fun getDiary(id: Int) = repository.getPhotoDiaryById(id)

    fun addDiary() {
        // TODO
    }
}