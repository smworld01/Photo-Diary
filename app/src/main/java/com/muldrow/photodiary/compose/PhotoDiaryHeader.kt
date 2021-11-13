package com.muldrow.photodiary.compose

import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

@Composable
fun PhotoDiaryHeader(editable: Boolean, imgUri: Uri?, registerImgUri: (Uri?) -> Unit) {
    
    if (editable) {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.OpenDocument(),
            onResult = registerImgUri)
        if (imgUri == null) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = "이곳을 클릭해서 사진을 넣어 보세요.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable {
                            launcher.launch(arrayOf("image/*"))
                        },
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            val contentResolver = LocalContext.current.contentResolver
            val img = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(contentResolver, imgUri)
                )
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, imgUri)
            }
            Image(bitmap = img.asImageBitmap(),
                contentDescription = "이미지에요",
                modifier = Modifier.fillMaxHeight().fillMaxWidth()
            )
        }
    }

}