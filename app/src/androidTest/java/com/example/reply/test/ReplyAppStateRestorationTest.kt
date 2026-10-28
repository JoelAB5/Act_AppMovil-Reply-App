package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import com.example.reply.R
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import com.example.reply.ui.theme.ReplyTheme
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailRetained_afterConfigChange() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            ReplyTheme { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }
        }
        openThirdEmail()
        assertSelectedEmailInDetails()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.navigation_back)
            .assertExists()

        restorationTester.emulateSavedInstanceStateRestore()

        assertSelectedEmailInDetails()
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.navigation_back)
            .assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailRetained_afterConfigChange() {
        val restorationTester = StateRestorationTester(composeTestRule)
        restorationTester.setContent {
            ReplyTheme { ReplyApp(windowSize = WindowWidthSizeClass.Expanded) }
        }
        openThirdEmail()
        assertSelectedEmailInDetails()

        restorationTester.emulateSavedInstanceStateRestore()

        assertSelectedEmailInDetails()
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_drawer).assertExists()
    }

    private fun openThirdEmail() {
        val email = LocalEmailsDataProvider.allEmails[2]
        // Scroll first so this works on short phone displays as well as tablets.
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(email.subject))
            .performScrollTo().assertIsDisplayed().performClick()
    }

    private fun assertSelectedEmailInDetails() {
        val body = composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
        val detailsTag = composeTestRule.activity.getString(R.string.details_screen)
        // Scope the assertion to the detail pane, rather than a matching list item.
        composeTestRule.onNode(hasText(body) and hasAnyAncestor(hasTestTag(detailsTag)))
            .assertExists()
    }
}
