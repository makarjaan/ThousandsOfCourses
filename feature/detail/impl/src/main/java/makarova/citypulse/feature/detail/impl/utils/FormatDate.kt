package makarova.citypulse.feature.detail.impl.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(startTimestamp: Long, endTimestamp: Long?): String {
    val startDate = Date(startTimestamp * 1000)
    val sdfDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val sdfTime = SimpleDateFormat("HH:mm", Locale.getDefault())

    return if (endTimestamp != null && endTimestamp > 0) {
        val endDate = Date(endTimestamp * 1000)
        val startTimeStr = sdfTime.format(startDate)
        val endTimeStr = sdfTime.format(endDate)

        if (startTimeStr == endTimeStr) {
            "${sdfDate.format(startDate)} $startTimeStr"
        } else {
            "${sdfDate.format(startDate)} $startTimeStr - $endTimeStr"
        }
    } else {
        "${sdfDate.format(startDate)} ${sdfTime.format(startDate)}"
    }
}