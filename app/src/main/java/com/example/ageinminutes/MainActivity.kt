package com.example.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    private var tvSelectedDate: TextView?=null
    private var tvDisplay: TextView?=null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDisplay=findViewById(R.id.tv6)
        tvSelectedDate=findViewById(R.id.tv4)
        val btnDatePicker: Button = findViewById(R.id.btn1)
        btnDatePicker.setOnClickListener {
            datePicker()
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun datePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val date = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate= "$dayOfMonth/${month+1}/$year"
                tvSelectedDate?.text =selectedDate
                val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate= sdf.parse(selectedDate)
                val selectedDateInMinutes= (theDate?.time)?.div(60000)
                val currentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes= (currentDate?.time)?.div(60000)
                val differenceInMinutes= currentDateInMinutes!! - selectedDateInMinutes!!
                tvDisplay?.text =differenceInMinutes.toString()
                tvDisplay?.visibility=View.VISIBLE
                tvSelectedDate?.visibility = View.VISIBLE
                val tv5: TextView=findViewById(R.id.tv5)
                tv5.visibility=View.VISIBLE
                val tv7: TextView=findViewById(R.id.tv7)
                tv7.visibility=View.VISIBLE
                                               },
            year,
            month,
            date).show()
    }
}