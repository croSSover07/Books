package com.example.developer.books.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.widget.DatePicker
import com.example.developer.books.R
import java.util.*

class DatePickerFragment:DialogFragment() {
    private lateinit var datePicker: DatePicker
    companion object {
        val EXTRA_DATE = "date"
        fun newInstance(): DatePickerFragment = DatePickerFragment()
    }
    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val v = LayoutInflater.from(activity)
                .inflate(R.layout.dialog_date, null)
        datePicker =v.findViewById(R.id.dialog_datepicker)
        return AlertDialog.Builder(activity)
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    val year = datePicker.year
                    val month = datePicker.month
                    val day = datePicker.dayOfMonth
                    val date = GregorianCalendar(year, month,day).time
                    sendResult(Activity.RESULT_OK, date) })
                .create()
    }
    private fun sendResult(resultCode:Int, date: Date) {
        if (targetFragment == null) {
            return
        }
        val intent = Intent()
        intent.putExtra(EXTRA_DATE, date)
        targetFragment.onActivityResult(targetRequestCode, resultCode, intent)
    }
}




