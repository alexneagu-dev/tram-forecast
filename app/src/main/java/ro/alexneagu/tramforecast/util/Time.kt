package ro.alexneagu.tramforecast.util

import java.util.*

object Time {

    fun checkTime(): Boolean {
        val startDay = Calendar.getInstance()
        startDay.set(Calendar.HOUR_OF_DAY, 0)
        startDay.set(Calendar.MINUTE, 0)
        startDay.set(Calendar.SECOND, 0)

        val midDay = Calendar.getInstance()
        midDay.set(Calendar.HOUR_OF_DAY, 12)
        midDay.set(Calendar.MINUTE, 1)
        midDay.set(Calendar.SECOND, 0)

        val timeOfDay = Calendar.getInstance()

        return timeOfDay.between(startDay, midDay)
    }

    private fun Calendar.between(first: Calendar, second: Calendar): Boolean {
        return after(first) && before(second)
    }

}