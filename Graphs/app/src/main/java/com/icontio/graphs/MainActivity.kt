package com.icontio.graphs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.icontio.graphs.ui.main.MainScreen

import com.shimmerresearch.android.Shimmer;
import com.shimmerresearch.android.guiUtilities.ShimmerBluetoothDialog;

import com.icontio.graphs.ui.theme.MyApplicationTheme
import com.icontio.graphs.ui.vico.MainScreenVico
import com.icontio.graphs.ui.vico.MainScreenVicoViewModel
import com.linc.audiowaveform.AudioWaveform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // charty
                    // only one possible line
                    // MainScreen(viewModel = MainViewModel())

                    // composable graphs
                    // only one gpossible line
                    // MainScreenCG()

                    // plot
                    // MainScreenPlot()

                    // composeble charts
                    // Fails to exception in their source files
                    // MainScreenCC()

                    // Vico
                    MainScreenVico(MainScreenVicoViewModel())

                    // YCharts
                    // more lines possible
                    // MainScreenYCharts()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme() {
        Greeting("Android")
    }
    var waveformProgress = remember { mutableStateOf(0F) }
    Box(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            AudioWaveform(
                modifier = Modifier.fillMaxWidth(),
                amplitudes = listOf(0,1,2,3,4,5,6,7,8,9,10),
                progress = waveformProgress.value,
                onProgressChange = { waveformProgress.value = it }
            )
            Spacer(modifier = Modifier
                .height(5.dp)
                .fillMaxWidth())
            Button(
                onClick = {
                    waveformProgress.value = waveformProgress.value + 1F
                }
            ) {
                Icon(Icons.Default.Add, "Add")
            }
        }
    }


}