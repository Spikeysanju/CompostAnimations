package com.spikeysanju.allaboutanimations.animations

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform

@Composable
fun BallCanvas(name: String) {
    var rotateAxis by remember { mutableStateOf(0f) }

    val rotateXAxis by animateFloatAsState(
        targetValue = rotateAxis,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMediumLow
        )
    )

//    val rotateXAxis by animateFloatAsState(
//        targetValue = rotateAxis,
//        animationSpec = tween(
//            durationMillis = 300,
//            delayMillis = 50,
//            easing = LinearOutSlowInEasing
//        )
//    )

    Canvas(modifier = Modifier.fillMaxSize().clickable {

        rotateAxis = if (rotateAxis.equals(45f)) 0f else 45f

    }) {



        // canvas measurement
        val canvasSize = size
        val canvasWidth = size.width
        val canvasHeight = size.height


        withTransform({
            translate(left = canvasWidth / 5F)
            rotate(degrees = rotateXAxis)
        }) {
            drawRect(
                color = Color.Black,
                topLeft = Offset(x = canvasWidth / 8F, y = canvasHeight / 4F),
                size = canvasSize / 2F
            )
        }



//        rotate(rotateAxis){
//            drawRect(
//                color = Color.Gray,
//                topLeft = Offset(x = canvasWidth / 3F, y = canvasHeight / 3F),
//                size = canvasSize / 3F
//            )
//        }
    }
}