package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.R
import com.example.reply.ui.ReplyApp
import com.example.reply.ui.theme.ReplyTheme
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation() {
        composeTestRule.setContent {
            ReplyTheme { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }
        }
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_bottom).assertExists()
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_rail).assertDoesNotExist()
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_drawer).assertDoesNotExist()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail() {
        composeTestRule.setContent {
            ReplyTheme { ReplyApp(windowSize = WindowWidthSizeClass.Medium) }
        }
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_rail).assertExists()
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_bottom).assertDoesNotExist()
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_drawer).assertDoesNotExist()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyUsingNavigationDrawer() {
        composeTestRule.setContent {
            ReplyTheme { ReplyApp(windowSize = WindowWidthSizeClass.Expanded) }
        }
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_drawer).assertExists()
        composeTestRule.onNodeWithTagForStringId(R.string.details_screen).assertExists()
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_bottom).assertDoesNotExist()
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_rail).assertDoesNotExist()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.navigation_back)
            .assertDoesNotExist()
    }
}
