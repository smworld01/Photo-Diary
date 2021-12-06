package com.muldrow.photodiary.compose

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PhotoDiaryBody(
    editable: Boolean = false,
    imgUri: Uri? = null,
    registerImgUri: (Uri?) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = registerImgUri)
    Box(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentSize()
        .background(color = Color.Gray.copy(alpha = 0.1f))
        .clickable(enabled = editable) { launcher.launch(arrayOf("image/*")) },
    ) {
        if (imgUri == null) {
            Text(
                text = if (editable) "이곳을 클릭해서 사진을 넣어 보세요." else "사진이 없어요.",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .align(Alignment.Center)
                ,
                textAlign = TextAlign.Center,
            )
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
                modifier = Modifier
                    .fillMaxSize()
            )
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
        modifier = modifier.fillMaxWidth().height(intrinsicSize = IntrinsicSize.Max),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        )
    )
}