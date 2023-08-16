package com.icontio.graphs.ui.vico

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class MainScreenVicoViewModel() : ViewModel() {
    val randomDataFlow = flow{
        while(true) {
            val randomDouble = Random.nextDouble(6.15, 45.2).toFloat()
            emit(randomDouble)
            delay(4)
        }
    }
}