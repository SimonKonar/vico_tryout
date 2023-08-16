package com.icontio.graphs.ui.vico


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
import androidx.compose.material3.darkColorScheme
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.himanshoe.charty.line.config.LineChartColors
import com.linc.audiowaveform.AudioWaveform
import com.linc.audiowaveform.model.AmplitudeType
import com.linc.audiowaveform.model.WaveformAlignment
import com.madrapps.plot.line.DataPoint
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.legend.legendItem
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.compose.style.ChartStyle
import com.patrykandpatrick.vico.compose.style.ProvideChartStyle
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.axis.Axis
import com.patrykandpatrick.vico.core.chart.composed.ComposedChart
import com.patrykandpatrick.vico.core.chart.copy
import com.patrykandpatrick.vico.core.chart.decoration.ThresholdLine
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.text.textComponent
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainScreenVico(
    viewModel: MainScreenVicoViewModel
) {

    var initialData1 = remember {
        mutableStateListOf(
            ChartEntryImp(0f, 0f),
            ChartEntryImp(1f, 0f),
            ChartEntryImp(2f, 0f),
            ChartEntryImp(3f, 0f),
            ChartEntryImp(4f, 0f),
            ChartEntryImp(5f, 0f),
            ChartEntryImp(6f, 0f),
            ChartEntryImp(7f, 0f),
        )
    }
    var initialData2 = remember {
        mutableStateListOf(
            ChartEntryImp(0f, 0f),
            ChartEntryImp(1f, 0f),
            ChartEntryImp(2f, 0f),
            ChartEntryImp(3f, 0f),
            ChartEntryImp(4f, 0f),
            ChartEntryImp(5f, 0f),
            ChartEntryImp(6f, 0f),
            ChartEntryImp(7f, 0f),
        )
    }
    var initialData3 = remember {
        mutableStateListOf(
            ChartEntryImp(0f, 0f),
            ChartEntryImp(1f, 0f),
            ChartEntryImp(2f, 0f),
            ChartEntryImp(3f, 0f),
            ChartEntryImp(4f, 0f),
            ChartEntryImp(5f, 0f),
            ChartEntryImp(6f, 0f),
            ChartEntryImp(7f, 0f),
        )
    }

//
//    var data2 = remember {
//        mutableStateListOf(
//            ChartEntryImp(0f, 5f),
//            ChartEntryImp(1f, 9f),
//            ChartEntryImp(2f, 25f),
//            ChartEntryImp(3f, 21f),
//            ChartEntryImp(4f, 16f),
//        )
//    }


    var data1 = ChartEntryModelProducer(entryCollections = listOf(initialData1))

    var data2 = ChartEntryModelProducer(entryCollections = listOf(initialData2))

    var data3 = ChartEntryModelProducer(entryCollections = listOf(initialData3))

    var composedDataChart =
        ChartEntryModelProducer(entryCollections = listOf(initialData3, initialData2))

    var average = remember {
        mutableStateOf(0f)
    }
    var maxValue = remember {
        mutableStateOf(0f)
    }

    var minValue = remember {
        mutableStateOf(46f)
    }
    var totalcount = remember {
        mutableStateOf(8f)
    }

    var count1 = remember {
        mutableStateOf(8f)
    }

    var count2 = remember {
        mutableStateOf(8f)
    }

    var count3 = remember {
        mutableStateOf(8f)
    }

    var stepCounter = remember {
        mutableStateOf(0)
    }

    var samplingMeanStepCounter = remember {
        mutableStateOf(0)
    }

    var bigMeanStepCounter = remember {
        mutableStateOf(0)
    }

    var samplingMean = remember {
        mutableStateListOf<Float>()
    }

    var bigMean = remember {
        mutableStateListOf<Float>()
    }

    val thresholdLines = listOf(
        ThresholdLine(maxValue.value, thresholdLabel = "max"),
        ThresholdLine(minValue.value, thresholdLabel = "min"),
        ThresholdLine(average.value, thresholdLabel = "avg")
    )

    LaunchedEffect(viewModel) {
        val coroutineScope = CoroutineScope(Dispatchers.Default)
        coroutineScope.launch {
//            stepCounter.value = ( stepCounter.value + 1 ) % 4
//            println(stepCounter.value)
            viewModel.randomDataFlow.collect {
                totalcount.value += 1
                if (it > maxValue.value) {
                    maxValue.value = it
                }
                if (it < minValue.value) {
                    minValue.value = it
                }
                stepCounter.value = (stepCounter.value + 1) % 8
                average.value = average.value + ((it - average.value) / totalcount.value)
                bigMean.add(it)
                if (bigMeanStepCounter.value == 39) {
                    initialData3.removeFirst()
                    initialData3.add(ChartEntryImp(count3.value, bigMean.sum() / 40))
                    count3.value += 1
                    bigMean = mutableStateListOf()
                }
                bigMeanStepCounter.value = (bigMeanStepCounter.value + 1) % 40
                println(stepCounter.value)
                if (stepCounter.value == 7) {
                    samplingMean.add(it)
                    if (samplingMeanStepCounter.value == 4) {
                        initialData2.removeFirst()
                        initialData2.add(ChartEntryImp(count2.value, samplingMean.sum() / 5))
                        count2.value += 1
                        samplingMean = mutableStateListOf()
                    }
                    samplingMeanStepCounter.value = (samplingMeanStepCounter.value + 1) % 5

                    val chartEntry = ChartEntryImp(count1.value, it)
                    count1.value += 1
                    withContext(Dispatchers.Default) {
                        initialData1.removeFirst()
                        initialData1.add(chartEntry)
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Chart(
//
//                chart = lineChart(decorations = listOf(ThresholdLine(20f))),
//                model = data1.getModel(),
//                startAxis = rememberStartAxis(),
////                bottomAxis = rememberBottomAxis(),
//            )
//
//            Spacer(
//                modifier = Modifier
//                    .height(5.dp)
//                    .fillMaxWidth()
//            )
            ProvideChartStyle(
                ChartStyle(
                    lineChart = ChartStyle.LineChart(
                        lines = listOf(
                            LineChart.LineSpec(lineColor = Color.Blue.toArgb()),
                            LineChart.LineSpec(lineColor = Color.Red.toArgb())
                        )
                    ),
                    axis = ChartStyle.Axis(
                        axisLabelColor = Color.Black,
                        axisGuidelineColor = Color.Black,
                        axisLineColor = Color.Black,
                    ),
                    columnChart = ChartStyle.ColumnChart(listOf()),
                    marker = ChartStyle.Marker(),
                    elevationOverlayColor = Color.Transparent

                )
            )
            {
                Chart(

                    chart = lineChart(
                        decorations = thresholdLines,
                        axisValuesOverrider = AxisValuesOverrider.fixed(maxY = maxValue.value + 2)
                    ),
                    model = composedDataChart.getModel(),
                    startAxis = rememberStartAxis(),
                    legend = verticalLegend(
                        items = listOf(
                            legendItem(
                                icon = shapeComponent(Shapes.pillShape, Color.Red),
                                label = textComponent(
                                    color = currentChartStyle.axis.axisLabelColor,
                                    textSize = 15.sp
                                ),
                                labelText = "Mean of 8x5 values"
                            ),
                            legendItem(
                                icon = shapeComponent(Shapes.pillShape, Color.Blue),
                                label = textComponent(
                                    color = currentChartStyle.axis.axisLabelColor,
                                    textSize = 15.sp
                                ),
                                labelText = "Mean of 40 values"
                            )
                        ),
                        iconSize = 8.dp,
                        iconPadding = 10.dp
                    )
//                bottomAxis = rememberBottomAxis(),
                )
            }
//            Chart(
//
//                chart = lineChart(
//                    decorations = thresholdLines,
//                    axisValuesOverrider = AxisValuesOverrider.fixed(maxY = maxValue.value + 2)
//                ),
//                model = data2.getModel(),
//                startAxis = rememberStartAxis(),
////                bottomAxis = rememberBottomAxis(),
//            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
                    .fillMaxWidth()
            )
//            Chart(
//
//                chart = lineChart(decorations = listOf(ThresholdLine(20f))),
//                model = data3.getModel(),
//                startAxis = rememberStartAxis(),
////                bottomAxis = rememberBottomAxis(),
//            )
//
//            Spacer(
//                modifier = Modifier
//                    .height(5.dp)
//                    .fillMaxWidth()
//            )
//            Button(
//                onClick = {
//                    data1.removeFirst()
////                    data2.removeFirst()
//                    data1.add(ChartEntryImp(count.value, Random.nextInt(0,16).toFloat()))
////                    data2.add(ChartEntryImp(count.value, count.value + 10f))
////                    data2.add(ChartEntryImp(count.value, count.value + 10f))
//
//                    count.value += 1
//                }
//            ) {
//                Icon(Icons.Default.Add, "Add")
//            }
            Spacer(
                modifier = Modifier
                    .height(5.dp)
                    .fillMaxWidth()
            )
        }
    }

}


