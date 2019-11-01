package com.zhuzichu.android.shared.extension

import android.text.format.DateUtils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class ParseDateFormat private constructor() {

    private val lock = Any()

    private val dateFormat: DateFormat

    init {
        dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        dateFormat.timeZone = TimeZone.getDefault()
    }

    fun format(date: Date): String {
        synchronized(lock) {
            return dateFormat.format(date)
        }
    }

    companion object {

        private val instance = ParseDateFormat()


        fun getTimeAgo(toParse: String?): CharSequence {
            try {
                val parsedDate = instance.dateFormat.parse(toParse!!)
                val now = System.currentTimeMillis()
                return DateUtils.getRelativeTimeSpanString(
                    parsedDate!!.time,
                    now,
                    DateUtils.SECOND_IN_MILLIS
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return "N/A"
        }

        fun getTimeAgo(parsedDate: Date?): CharSequence {
            if (parsedDate != null) {
                val now = System.currentTimeMillis()
                return DateUtils.getRelativeTimeSpanString(
                    parsedDate.time,
                    now,
                    DateUtils.SECOND_IN_MILLIS
                )
            }
            return "N/A"
        }

        fun toGithubDate(date: Date): String {
            return instance.format(date)
        }

        fun prettifyDate(timestamp: Long): String {
            return SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(Date(timestamp))
        }

        fun getDateFromString(date: String): Date? {
            try {
                return SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(date)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return null
        }

        private fun getDateByDays(days: Int): String {
            val cal = Calendar.getInstance()
            val s = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            cal.add(Calendar.DAY_OF_YEAR, days)
            return s.format(Date(cal.timeInMillis))
        }

        val lastWeekDate: String
            get() = getDateByDays(-7)
    }

}