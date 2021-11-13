package com.muldrow.photodiary

import com.muldrow.photodiary.data.PhotoDiary
import java.text.SimpleDateFormat
import java.util.*

object PhotoDiaryProvider {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    val diaryList = listOf(
        PhotoDiary(
            0,
            null,
            sdf.parse("2021-11-10"),
            "오늘 하루"
        ),
        PhotoDiary(
            1,
            null,
            sdf.parse("2021-11-11"),
            "열심히"
        ),
        PhotoDiary(
            2,
            null,
            sdf.parse("2021-11-11"),
            "열심히"
        ),
        PhotoDiary(
            3,
            null,
            sdf.parse("2021-11-11"),
            "열심히"
        ),
        PhotoDiary(
            4,
            null,
            sdf.parse("2021-11-11"),
            "열심히"
        ),
        PhotoDiary(
            5,
            null,
            sdf.parse("2021-11-11"),
            "열심히"
        )
    )
}