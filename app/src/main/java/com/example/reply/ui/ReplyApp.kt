package com.example.reply.ui

import androidx.compose.runtime.Composable
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reply.data.Email
import com.example.reply.data.MailboxType
import com.example.reply.ui.utils.ReplyNavigationType
import com.example.reply.ui.utils.ReplyContentType

@Composable
fun ReplyApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val viewModel: ReplyViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    val navigationType = when (windowSize) {
        WindowWidthSizeClass.Medium -> ReplyNavigationType.NAVIGATION_RAIL
        WindowWidthSizeClass.Expanded -> ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
        else -> ReplyNavigationType.BOTTOM_NAVIGATION
    }
    val contentType = if (windowSize == WindowWidthSizeClass.Expanded) {
        ReplyContentType.LIST_AND_DETAIL
    } else {
        ReplyContentType.LIST_ONLY
    }

    ReplyHomeScreen(
        navigationType = navigationType,
        contentType = contentType,
        replyUiState = replyUiState,
        onTabPressed = { mailboxType: MailboxType ->
            viewModel.updateCurrentMailbox(mailboxType = mailboxType)
            viewModel.resetHomeScreenStates()
        },
        onEmailCardPressed = { email: Email ->
            viewModel.updateDetailsScreenStates(
                email = email
            )
        },
        onDetailScreenBackPressed = {
            viewModel.resetHomeScreenStates()
        },
        modifier = modifier
    )
}
