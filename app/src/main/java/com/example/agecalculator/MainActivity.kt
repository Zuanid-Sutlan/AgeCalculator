package com.example.agecalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }


    }


    private fun clickDatePicker() {
        val myCalender = Calendar.getInstance()

        val cYear = myCalender[Calendar.YEAR]
        val cMonth = myCalender[Calendar.MONTH]
        val cDay = myCalender[Calendar.DAY_OF_MONTH]

        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                val tvSelectedDate = findViewById<TextView>(R.id.tv_date)
                tvSelectedDate.text = selectedDate


                val dateInMinutes = (year * 365 * 24 * 60) + (month * 30 * 24 * 60) + (dayOfMonth * 24 * 60)


                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate!!.time / 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes = currentDate!!.time / 60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tv_age_in_minutes)
                tvSelectedDateInMinutes.text = differenceInMinutes.toString()


            },
            cYear,
            cMonth,
            cDay

        ).apply {
            datePicker.maxDate = System.currentTimeMillis() - 86400000
        }.show()



    }
}
