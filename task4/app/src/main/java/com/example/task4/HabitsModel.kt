package com.example.task4

import com.example.task4.Filter.FilterOrdering
import com.example.task4.Filter.HabitFilter
import com.example.task4.Filter.OrderingType
import com.example.task4.Habit.HabitInfo
import com.example.task4.Habit.HabitType

object HabitsModel {
    private val habits: ArrayList<HabitInfo> = ArrayList()
    private val reg = "[^\\w]*(\\w+)[^\\w]*".toRegex()

    fun addHabit(pos: Int, habit: HabitInfo)
    {
        if (pos == -1)
            habits.add(habit)
        else
            habits[pos] = habit
    }

    fun getHabits(habitType: HabitType, filter: HabitFilter): ArrayList<HabitInfo>
    {
        val filteredHabits = ArrayList<HabitInfo>()
        for (habit in habits)
        {
            if (habit.type == habitType) {
                if (!reg.findAll(filter.habitName).any() || habit.name!!.contains(filter.habitName))
                    filteredHabits.add(habit)
            }
        }
        if (filter.filterOrdering != FilterOrdering.None) {
            val filterOrdering = filter.filterOrdering
            if (filter.orderingType == OrderingType.Ascending) {
                filteredHabits.sortBy { if (filterOrdering == FilterOrdering.Periodicity) it.periodicity else it.priorityNumeric }
            } else if (filter.orderingType == OrderingType.Descending) {
                filteredHabits.sortByDescending { if (filterOrdering == FilterOrdering.Periodicity) it.periodicity else it.priorityNumeric }
            }
        }

        return filteredHabits
    }

    fun loadMain(function: (ArrayList<HabitInfo>, HabitFilter) -> Unit){
        function.invoke(habits, HabitFilter())
    }

    fun loadEditor(function: (HabitInfo) -> Unit)
    {
        function.invoke(HabitInfo.defaultInstance())
    }
}