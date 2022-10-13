package com.spikeysanju.allaboutanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.spikeysanju.allaboutanimations.animations.AnimatingBox
import com.spikeysanju.allaboutanimations.animations.SpinSphere
import com.spikeysanju.allaboutanimations.ui.theme.AllAboutAnimationsTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllAboutAnimationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AnimatingBox()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AllAboutAnimationsTheme {
        SpinSphere(
            ringColor = MaterialTheme.colors.primary,
            circleColor = MaterialTheme.colors.onSurface,
        )
    }
}