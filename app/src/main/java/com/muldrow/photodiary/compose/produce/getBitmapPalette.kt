package com.muldrow.photodiary.compose.produce

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.palette.graphics.Palette
import com.muldrow.photodiary.util.createPaletteSync


@Composable
fun getBitmapPalette(bitmap: Bitmap?) =
    produceState<Palette?>(initialValue = null, bitmap) {
        value = if (bitmap == null) {
            null
        } else {
            createPaletteSync(bitmap)
        }
    }


@Composable
fun getBitmapPalette(uri: Uri?): State<Palette?> {
    val contentResolver = LocalContext.current.contentResolver

    val bitmap = when {
        uri == null -> null
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri))
                .copy(Bitmap.Config.RGBA_F16, true)
        }
        else -> {
            MediaStore.Images.Media.getBitmap(contentResolver, uri)
        }
    }

    return produceState<Palette?>(initialValue = null, uri) {
        value = if (bitmap == null) {
            null
        } else {
            createPaletteSync(bitmap)
        }
    }
}