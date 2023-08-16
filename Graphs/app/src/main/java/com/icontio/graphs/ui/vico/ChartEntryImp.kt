package com.icontio.graphs.ui.vico


import com.patrykandpatrick.vico.core.entry.ChartEntry

class ChartEntryImp(
    override val x: Float,
    override val y: Float
): ChartEntry {
    override fun withY(y: Float): ChartEntry {
        return ChartEntryImp(0f, y)
    }
}

