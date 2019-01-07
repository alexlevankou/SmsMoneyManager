package by.alexlevankou.smsmoneymanager.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import java.util.*


class DatePickerFragment() : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var callbackListener: OnDatePickListener? = null

    interface OnDatePickListener {
        fun onDialogPositiveClick(date: Date?)
        fun onDialogCancelClick(dialog: DialogFragment)
    }

    override fun onAttach(activity: Context) {
        super.onAttach(activity)

        try {
            callbackListener = activity as OnDatePickListener?

        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement OnDateSetListener.")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var year = arguments?.getInt("year")
        var month = arguments?.getInt("month")
        var day = arguments?.getInt("day")

        if(year == null || month == null || day == null){
            val c = Calendar.getInstance()
            year = c.get(Calendar.YEAR)
            month = c.get(Calendar.MONTH)
            day = c.get(Calendar.DAY_OF_MONTH)
        }

        return DatePickerDialog(activity, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val date = calendar.time
        callbackListener?.onDialogPositiveClick(date)
    }
}