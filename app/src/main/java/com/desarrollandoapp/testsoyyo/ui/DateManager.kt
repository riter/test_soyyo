package com.desarrollandoapp.testsoyyo.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

object DateManager {

    fun getCurrentDate(): Calendar {
        return Calendar.getInstance()
    }

    fun getLastDayDate(daysAgo: Int): Calendar {
        val current = getCurrentDate()
        current.add(Calendar.DATE, daysAgo * -1)
        return current
    }

    // Ej: 2022-12-01
    @SuppressLint("SimpleDateFormat")
    fun dateToStringYYYYMMDD(date: Calendar): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return simpleDateFormat.format(date.time)
    }

    fun showRangeDateDialogUi(context: Context, callback: (startDate: Calendar, endDate: Calendar) -> Unit) {
        val date = getCurrentDate()
        val year = date[Calendar.YEAR]
        val month = date[Calendar.MONTH]
        val day = date[Calendar.DAY_OF_MONTH]

        val startDatePickerDialog = DatePickerDialog(context,
            { _: DatePicker?, year1: Int, month1: Int, dayOfMonth: Int ->
                val startDate = getCurrentDate()
                startDate[Calendar.YEAR] = year1
                startDate[Calendar.MONTH] = month1
                startDate[Calendar.DAY_OF_MONTH] = dayOfMonth

                val endDatePickerDialog = DatePickerDialog(context,
                    { _: DatePicker?, year2: Int, month2: Int, dayOfMonth2: Int ->
                        val endDate = getCurrentDate()
                        endDate[Calendar.YEAR] = year2
                        endDate[Calendar.MONTH] = month2
                        endDate[Calendar.DAY_OF_MONTH] = dayOfMonth2

                        callback.invoke(startDate, endDate)

                    }, year1, month1, dayOfMonth
                )

                endDatePickerDialog.datePicker.minDate = startDate.timeInMillis - 1000
                endDatePickerDialog.datePicker.maxDate = date.timeInMillis
                endDatePickerDialog.show()

            }, year, month, day)

        startDatePickerDialog.datePicker.maxDate = date.timeInMillis
        startDatePickerDialog.show()
    }
}