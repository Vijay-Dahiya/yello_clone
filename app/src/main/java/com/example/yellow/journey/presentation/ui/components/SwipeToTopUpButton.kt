package com.example.yellow.journey.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.yellow.journey.presentation.ui.states.SwipeState
import kotlin.math.roundToInt

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun SwipeToTopUpButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onSwipeComplete: () -> Unit
) {
    var containerWidth by remember { mutableStateOf(0) }
    val circleSize = 60.dp
    val circlePx = with(LocalDensity.current) { circleSize.toPx() }

    val swipeableState = rememberSwipeableState(SwipeState.INITIAL)

    LaunchedEffect(swipeableState.currentValue) {
        if (swipeableState.currentValue == SwipeState.COMPLETED && !isLoading) {
            onSwipeComplete()
            swipeableState.animateTo(SwipeState.INITIAL)
        }
    }

    val anchors = remember(containerWidth) {
        mapOf(
            0f to SwipeState.INITIAL,
            (containerWidth - circlePx) to SwipeState.COMPLETED
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(circleSize)
            .onSizeChanged { containerWidth = it.width }
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .background(Color(0xFF4D4D4D), shape = RoundedCornerShape(30.dp))
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        } else {
            Text(
                text = "SLIDE TO TOPUP",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        val offsetX = swipeableState.offset.value
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .size(circleSize)
                .background(Color(0xFFFFC107), CircleShape)
        ) {
            if (!isLoading) {
                Text(
                    text = "→",
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 22.sp,
                    color = Color.Black
                )
            }
        }
    }
}