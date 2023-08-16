package com.icontio.graphs.ui.main

import android.annotation.SuppressLint
import android.graphics.Outline
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.himanshoe.charty.common.ChartData
import com.himanshoe.charty.common.ChartDataCollection
import com.himanshoe.charty.common.config.ChartDefaults
import com.himanshoe.charty.line.LineChart
import com.himanshoe.charty.line.config.LineChartColors
import com.himanshoe.charty.line.config.LineChartDefaults
import com.linc.audiowaveform.AudioWaveform
import com.linc.audiowaveform.model.AmplitudeType
import com.linc.audiowaveform.model.WaveformAlignment
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainScreen(
//    viewModel: MainViewModel
){
    var waveformProgress = remember { mutableStateOf(1F) }
    var ta = remember {
        mutableStateOf(mutableStateListOf<Int>(1,5,88,25,33,99,2,5,7,15,68,99,33,22,11))
    }

    var amplitudes = remember {
        mutableStateListOf<Int>()
    }
    var t = remember {
        mutableStateOf("")
    }
    var a = remember {
        mutableStateOf(mutableListOf<Int>(1, 2,3,4,5,6,7,8,9))
    };

    var dd = remember { mutableStateListOf(ChartDataImp("a", 0, 5F),
        ChartDataImp("a", 1, 10F),
        ChartDataImp("a", 2, 12F),) }

    var data = remember {
        mutableStateOf(ChartDataCollection(
            dd,
        ))
    }

    var data2 = remember {
        mutableStateOf(ChartDataCollection(mutableListOf(
            ChartDataImp("b", 0, 10F),
            ChartDataImp("b", 1, 20F),
            ChartDataImp("b", 2, 30F)
        )
        ))
    }

    val lifecycleOwner = LocalLifecycleOwner.current

//    LaunchedEffect(true){
//        lifecycleOwner.lifecycleScope.launchWhenCreated {
//            viewModel.amplitudes.collectLatest { newList ->
//                amplitudes.clear()
//                amplitudes.addAll(newList)
//            }
//        }
//    }

    var count = 50;
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            Box(){
                LineChart(
                    dataCollection = data.value,
                    modifier = Modifier.height(200.dp),
                    padding = 16.dp,
                    axisConfig = ChartDefaults.axisConfigDefaults(),
                    radiusScale = 0.02f,
                    lineConfig = LineChartDefaults.defaultConfig(),
                    chartColors = LineChartColors(
                        lineColor = listOf<Color>(Color.Green, Color.Green),
                        dotColor = listOf<Color>(Color.Green, Color.Green),
                        backgroundColors = listOf<Color>(Color.Transparent, Color.Transparent)
                ))
            }

            Spacer(modifier = Modifier
                .height(5.dp)
                .fillMaxWidth())
            Button(
                onClick = {
                    ta.value.add(count)
                    a.value.add(count)
//                    viewModel.amplitudes.value.add(count)
//                    t.value = viewModel.amplitudes.value.toString()
                    dd.add(ChartDataImp("b", 3, 5.34F))
                    // data.value = ChartDataCollection(data.value.data + ChartDataImp("b", 3, 5.34F))

                }
            ) {
                Icon(Icons.Default.Add, "Add")
            }
            Spacer(modifier = Modifier
                .height(5.dp)
                .fillMaxWidth())
            Text(ta.value.toList().toString())
        }
    }
}


