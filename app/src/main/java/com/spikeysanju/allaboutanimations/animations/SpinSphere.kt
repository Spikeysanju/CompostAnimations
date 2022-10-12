package com.spikeysanju.allaboutanimations.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spikeysanju.allaboutanimations.R
import kotlinx.coroutines.launch

@Composable
fun SpinSphere(modifier: Modifier = Modifier, ringColor: Color, circleColor: Color) {
    val infiniteTransition = rememberInfiniteTransition()


    val rotateAxis by infiniteTransition.animateFloat(
        initialValue = 30f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        )
    )

    val rotateAxis2 by infiniteTransition.animateFloat(
        initialValue = 360f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )


    val angle = remember {
        Animatable(0f)
    }

    LaunchedEffect(angle) {
        launch {
            angle.animateTo(360f, animationSpec = tween(13000))
        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(modifier = modifier
            .size(300.dp)
            .drawBehind {
                // canvas measurement
                val canvasSize = size
                val canvasWidth = size.width
                val canvasHeight = size.height

//                // circular ring
//                drawCircle(
//                    color = ringColor,
//                    center = Offset(canvasWidth / 2f, canvasHeight / 2f),
//                    radius = canvasWidth / 3F,
//                    style = Stroke(30F),
//                )

//                // draw arc
//                drawArc(
//                    brush = Brush.verticalGradient(listOf(Color.Blue, Color.Cyan, Color.Green)),
//                    startAngle = 90f,
//                    sweepAngle = 180f,
//                    useCenter = false,
//                    style = Stroke(width = 30f, cap = StrokeCap.Round)
//                )

                rotate(rotateAxis) {
                    drawRoundRect(
                        size = canvasSize / 5F,
                        color = Color.Yellow,
                        cornerRadius = CornerRadius(16.dp.toPx())
                    )
                }

                rotate(angle.value) {
                    drawRoundRect(
                        size = canvasSize / 5F,
                        color = Color.Red,
                        cornerRadius = CornerRadius(16.dp.toPx())
                    )
                }

                // circle
                drawCircle(
                    color = circleColor,
                    center = Offset(canvasWidth / 2f, canvasHeight / 2f),
                    radius = canvasWidth / 4f,
                )


            }
            .clickable {
                // todo: add onclick action
            }, contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_flame),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp, 24.dp),
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}
