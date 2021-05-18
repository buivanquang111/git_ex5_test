package com.example.monstartlab_ex5

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarFragment : Fragment(),CalendarAdapter.OnItemCLick {
    private lateinit var selectDate: LocalDate

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_calendar,container,false)
        var bundle: Bundle? = arguments
        if (bundle != null){
            var str: String? = bundle.getString("list")
        }
        selectDate = LocalDate.now()
        setMonthView()
        return view
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun setMonthView(){
        textViewName.text = monthYearFromDate(selectDate)
        var dayInMonth: ArrayList<String> = dayInMonthArray(selectDate)
        var calendarAdapter: CalendarAdapter = CalendarAdapter(dayInMonth,this)
        var layoutManager: GridLayoutManager = GridLayoutManager(context,7)
        view?.recyclerView?.layoutManager = layoutManager
        view?.recyclerView?.adapter = calendarAdapter
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
    fun monthYearFromDate(date: LocalDate): String{
        var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, dayText: String) {
        Toast.makeText(context,"select date "+dayText+" "+monthYearFromDate(selectDate),Toast.LENGTH_SHORT).show()
    }


}