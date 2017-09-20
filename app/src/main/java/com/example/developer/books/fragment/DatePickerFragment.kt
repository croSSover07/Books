package com.example.developer.books.fragment

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.DatePicker
import com.example.developer.books.R
import java.util.*

class DatePickerFragment : DialogFragment() {
    private lateinit var datePicker: DatePicker

    companion object {
        const val EXTRA_DATE = "date"
        fun newInstance() = DatePickerFragment()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(activity)
                .setView(R.layout.dialog_date)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, { _, _ ->
                    val year = datePicker.year
                    val month = datePicker.month
                    val day = datePicker.dayOfMonth
                    val date = GregorianCalendar(year, month, day).time
                    sendResult(Activity.RESULT_OK, date)
                }).show()
//        TODO: Можно опустить вызов create(), так как show так же возвращает инстанс созданного диалога
//                .create()
//        dialog.show()

        datePicker = dialog.findViewById(R.id.dialog_datepicker) as DatePicker
        return dialog
    }

    private fun sendResult(resultCode: Int, date: Date) {
        targetFragment ?: return
        val intent = Intent()
        intent.putExtra(EXTRA_DATE, date)
        targetFragment.onActivityResult(targetRequestCode, resultCode, intent)
    }
}