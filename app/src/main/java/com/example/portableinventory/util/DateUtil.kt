package com.example.portableinventory.util

import android.app.DatePickerDialog
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun Fragment.createDatePicker(setChosenDateToViewCallback: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val currDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currMonth = calendar.get(Calendar.MONTH)
        val currYear = calendar.get(Calendar.YEAR)
        val picker = DatePickerDialog(
            requireActivity(),
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate =
                    "${monthOfYear + 1}/$dayOfMonth/$year"
                        .convertDateFormat(DateFormat.MM_dd_yyyy, DateFormat.MMM_dd_yyyy)
                setChosenDateToViewCallback(selectedDate)
            },
            currYear,
            currMonth,
            currDay
        )
        calendar.clear()
        picker.show()
    }

    fun String.convertDateFormat(from: String, to: String): String {
        return SimpleDateFormat(from, Locale.US).parse(this)?.let { date ->
            SimpleDateFormat(to, Locale.US).format(date).toString()
        } ?: ""
    }

    fun dateToString(date: Date): String =
        SimpleDateFormat(DateFormat.MMM_dd_yyyy, Locale.US).format(date)

    fun stringToDate(dateStr: String) =
        SimpleDateFormat(DateFormat.MMM_dd_yyyy, Locale.US).parse(dateStr) ?: Date()
}

