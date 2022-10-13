package com.spikeysanju.allaboutanimations.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

@Composable
fun AnimatingBox(
    modifier: Modifier = Modifier,
    boxState: BoxState = BoxState.SPINNING,
    speedState: SpeedState = SpeedState.LOW
) {

    val box = remember {
        mutableStateOf(BoxState.IDLE)
    }

    val speed = remember {
        mutableStateOf(SpeedState.LOW)
    }

    val transitionData = updateTransitionData(box.value, speed.value)
    val infiniteTransition = rememberInfiniteTransition()

    // rotate animation
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = transitionData.sweep,
        animationSpec = infiniteRepeatable(
            animation = tween(transitionData.speed, easing = LinearEasing)
        )
    )

    Box(
        modifier = modifier
            .scale(transitionData.scale)
            .size(300.dp)
            .drawBehind {
                rotate(angle) {
                    // UI tree
                    drawRoundRect(
                        color = transitionData.color,
                        cornerRadius = CornerRadius(16.dp.toPx()),
                        size = Size(300f, 300f),

                        )

                }


            }, contentAlignment = Alignment.Center
    ) {

        Column(modifier = modifier.fillMaxWidth()) {
            Button(onClick = {
                box.value = if (box.value == BoxState.IDLE) BoxState.SPINNING else BoxState.IDLE

            }) {
                Text(text = "Box ${box.value}", color = Color.White)
            }

            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )

            Button(onClick = {
                speed.value =
                    if (speed.value == SpeedState.HIGH) SpeedState.LOW else SpeedState.HIGH
            }) {
                Text(text = "Speed ${speed.value}", color = Color.White)
            }

        }
    }
}

// Animation states
enum class BoxState {
    IDLE,
    SPINNING
}

// Speed state
enum class SpeedState {
    LOW,
    MEDIUM,
    HIGH
}

// Holds the animation values.
private class TransitionData(
    color: State<Color>,
    sweep: State<Float>,
    speed: State<Int>,
    scale: State<Float>
) {
    val color by color
    val sweep by sweep
    val speed by speed
    val scale by scale

}


// Create a Transition and return its animation values.
@Composable
private fun updateTransitionData(boxState: BoxState, speedState: SpeedState): TransitionData {
    val transition = updateTransition(targetState = boxState, label = "main animation")
    val speedTransition = updateTransition(targetState = speedState, label = "speed animation")

    val color = transition.animateColor(label = "color transition") { state ->
        when (state) {
            BoxState.IDLE -> MaterialTheme.colors.onPrimary
            BoxState.SPINNING -> MaterialTheme.colors.onSecondary
        }
    }

    val sweep = transition.animateFloat(label = "sweep transition") { state ->
        when (state) {
            BoxState.IDLE -> 0f
            BoxState.SPINNING -> 360f
        }
    }

    val scale = transition.animateFloat(label = "scale transition") { state ->
        when (state) {
            BoxState.IDLE -> 1f
            BoxState.SPINNING -> 1.1f
        }
    }

    val speed = speedTransition.animateInt(label = "speed") { state ->

        when (state) {
            SpeedState.LOW -> 5000
            SpeedState.MEDIUM -> 3000
            SpeedState.HIGH -> 400
        }
    }


    return remember(transition) { TransitionData(color, sweep, speed, scale) }
}