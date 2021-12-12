package com.muldrow.photodiary.compose.produce

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.palette.graphics.Palette
import com.muldrow.photodiary.room.entities.PhotoDiaryWithContents
import com.muldrow.photodiary.util.createPaletteSync
import com.muldrow.photodiary.viewmodel.DiaryViewModel

@Composable
fun loadPhotoDiary(uid: Int?, viewModel: DiaryViewModel) =
    produceState<PhotoDiaryWithContents?>(initialValue = null, uid, viewModel) {
        value = if (uid == null) {
            null
        } else {
            viewModel.getDiary(uid)
        }
    }