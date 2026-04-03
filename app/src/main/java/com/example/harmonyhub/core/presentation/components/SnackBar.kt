package com.example.harmonyhub.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.harmonyhub.core.models.SnackBar
import com.example.harmonyhub.core.models.SnackBarDuration
import com.example.harmonyhub.core.models.icon
import com.example.harmonyhub.core.models.typeColor
import com.example.harmonyhub.core.services.SnackBarManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BoxScope.SnackBar() {
    var currentSnackBar by remember { mutableStateOf<SnackBar?>(null) }
    var isVisible by remember { mutableStateOf(false) }

    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    LaunchedEffect(Unit) {
        SnackBarManager.data.collectLatest { snackBar ->
            if (isVisible) {
                isVisible = false
                delay(150)
            }
            currentSnackBar = snackBar
            isVisible = true
            
            if (snackBar.duration != SnackBarDuration.INDEFINITE) {
                val durationMillis = when (snackBar.duration) {
                    SnackBarDuration.SHORT -> 3500L
                    SnackBarDuration.LONG -> 7000L
                    else -> 3500L
                }
                delay(durationMillis)
                isVisible = false
            }
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = topPadding + 16.dp, start = 16.dp, end = 16.dp)
        ) {
            currentSnackBar?.let { snack ->
                SnackBarContent(snack) {
                    isVisible = false
                }
            }
        }
    }
}

@Composable
private fun SnackBarContent(
    snackBar: SnackBar,
    onDismiss: () -> Unit
) {
    if (snackBar is SnackBar.CustomSnackBar) {
        Box(modifier = Modifier.fillMaxWidth()) {
            snackBar.content(onDismiss)
        }
        return
    }

    val typeColor = snackBar.typeColor
    val backgroundColor = MaterialTheme.colorScheme.surface
    val contentColor = MaterialTheme.colorScheme.onSurface
    val icon = snackBar.icon
    
    val title: String
    val description: String?
    val actionTitle: String?
    val action: (() -> Unit)?

    when (snackBar) {
        is SnackBar.InfoSnackBar -> {
            title = snackBar.title
            description = snackBar.description
            actionTitle = snackBar.actionTitle
            action = snackBar.action
        }
        is SnackBar.ErrorSnackBar -> {
            title = snackBar.title
            description = snackBar.description
            actionTitle = snackBar.actionTitle
            action = snackBar.action
        }
        is SnackBar.SuccessSnackBar -> {
            title = snackBar.title
            description = snackBar.description
            actionTitle = snackBar.actionTitle
            action = snackBar.action
        }
        is SnackBar.WarningSnackBar -> {
            title = snackBar.title
            description = snackBar.description
            actionTitle = snackBar.actionTitle
            action = snackBar.action
        }
        else -> return
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Touch of color: Side indicator
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(typeColor)
            )
            
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = typeColor,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = contentColor,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )
                    if (!description.isNullOrEmpty()) {
                        Text(
                            text = description,
                            color = contentColor.copy(alpha = 0.7f),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        )
                    }
                }
                
                if (actionTitle != null && action != null) {
                    TextButton(
                        onClick = {
                            action()
                            onDismiss()
                        }
                    ) {
                        Text(
                            text = actionTitle,
                            color = typeColor,
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                } else {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Dismiss",
                            tint = contentColor.copy(alpha = 0.5f),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}