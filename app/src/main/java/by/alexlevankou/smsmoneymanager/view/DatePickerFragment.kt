package by.alexlevankou.smsmoneymanager.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import android.widget.TextView
import by.alexlevankou.smsmoneymanager.R
import by.alexlevankou.smsmoneymanager.model.Operation
import by.alexlevankou.smsmoneymanager.viewmodel.EditViewModel
import java.text.SimpleDateFormat
import java.util.*


class DatePickerFragment() : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var mOperation : Operation?= null
    private var mEditViewModel : EditViewModel? = null

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mEditViewModel = ViewModelProviders.of(activity!!).get(EditViewModel::class.java)
        mEditViewModel?.getOperation()?.observe(activity!!, Observer<Operation> { operation -> mOperation = operation })
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        val date = calendar.time
        val dateText = activity?.findViewById<TextView>(R.id.date_text)
        val dateFormat = SimpleDateFormat("dd MMMM")
        dateText?.setText(dateFormat.format(date))
        mOperation?.setDate(date)
        mEditViewModel?.updateOperation(mOperation)
    }
}