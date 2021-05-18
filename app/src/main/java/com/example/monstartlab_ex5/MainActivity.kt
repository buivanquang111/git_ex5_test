package com.example.monstartlab_ex5

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(),CalendarAdapter.OnItemCLick {
    private lateinit var selectDate: LocalDate

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        selectDate = LocalDate.now()
        setMonthView()
        imageViewBack.setOnClickListener {
            selectDate = selectDate.minusMonths(1)
            setMonthView()
        }
        imageViewNext.setOnClickListener {
            selectDate =selectDate.plusMonths(1)
            setMonthView()
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun setMonthView(){
        textViewName.text = monthYearFromDate(selectDate)
        var dayInMonth: ArrayList<String> = dayInMonthArray(selectDate)
        var calendarAdapter: CalendarAdapter = CalendarAdapter(dayInMonth,this)
        var layoutManager: GridLayoutManager = GridLayoutManager(applicationContext,7)
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL))
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = calendarAdapter
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun dayInMonthArray(date: LocalDate): ArrayList<String>{
        var daysInMonthArray: ArrayList<String> = ArrayList()
        var yearMonth: YearMonth = YearMonth.from(date)
        var daysInMonth = yearMonth.lengthOfMonth()
        var firstOfMonth=selectDate.withDayOfMonth(1)
        var dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1 until 43){
            /*if (i <= dayOfWeek){
                daysInMonthArray.add((i-dayOfWeek+daysInMonth).toString())
            }
            else if (i> daysInMonth+dayOfWeek){
                daysInMonthArray.add((i-daysInMonth-dayOfWeek).toString())
            }
            else{
                daysInMonthArray.add((i-dayOfWeek).toString())
            }*/
            when{
                (i <= dayOfWeek) -> {
                    daysInMonthArray.add((i-dayOfWeek+daysInMonth).toString())
                }
                (i> daysInMonth+dayOfWeek) ->{
                    daysInMonthArray.add((i-daysInMonth-dayOfWeek).toString())
                }
                else ->{
                    daysInMonthArray.add((i-dayOfWeek).toString())
                }
            }
        }
        return daysInMonthArray
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun monthYearFromDate(date:LocalDate): String{
        var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, dayText: String) {
        if (!dayText.equals("")){
            Toast.makeText(this,"select date "+dayText+" "+monthYearFromDate(selectDate),Toast.LENGTH_SHORT).show()

        }
    }

}