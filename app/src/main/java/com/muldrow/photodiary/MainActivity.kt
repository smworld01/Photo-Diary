package com.muldrow.photodiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.muldrow.photodiary.compose.screen.*
import com.muldrow.photodiary.ui.theme.PhotoDiaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent.copy(0.1f),
                    darkIcons = useDarkIcons
                )

            }


            val navController = rememberNavController()
            PhotoDiaryTheme {
                NavHost(navController = navController, startDestination = "diary/list") {
                    composable("diary/list") {
                        DiaryListScreen(navController = navController)
                    }
                    composable("diary/write") {
                        DiaryWriteScreen()
                    }
                    composable(
                        "diary/item/{diaryId}",
                        arguments = listOf(navArgument("diaryId") {type = NavType.IntType})
                    ) {
                        DiaryScreen(uid = it.arguments?.getInt("diaryId", 0))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotoDiaryTheme {

    }
}