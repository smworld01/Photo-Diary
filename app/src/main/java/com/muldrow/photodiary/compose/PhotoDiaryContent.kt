package com.muldrow.photodiary.compose

import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PhotoDiaryContent(
    modifier: Modifier = Modifier,
    editable: Boolean = false,
    imgUri: Uri? = null,
    setImgUri: ((Uri?) -> Unit)? = null,
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = {
            if (it != null) {
                val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION

                context.contentResolver.takePersistableUriPermission(it, takeFlags)

                setImgUri?.invoke(it)
            }
        }
    )
    Box(modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentSize()
        .background(color = Color.Gray.copy(alpha = 0.1f))
        .clickable(enabled = editable) { launcher.launch(arrayOf("image/*")) },
    ) {
        if (imgUri == null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Text(
                    text = if (editable) "이곳을 클릭해서 사진을 넣어 보세요." else "사진이 없어요.",
                    modifier = Modifier
                        .align(Alignment.Center)
                    ,
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
                contentDescription = "이미지",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )

            if (editable) {
                IconButton(
                    onClick = { setImgUri?.invoke(null) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Delete")
                }
            }
        }
    }
}

@Composable
fun PhotoDiaryText(
    text: String, onTextChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier.fillMaxWidth()
    )
}