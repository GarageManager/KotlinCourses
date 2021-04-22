package com.example.task4.ViewModels.HabitsList

import com.example.task4.HabitInfo
import com.example.task4.HabitType

class HabitsModel {
    private val profile = HabitsProfile()
    private val reg = "[^\\w]*(\\w+)[^\\w]*".toRegex()

    fun getHabits(habitType: HabitType): ArrayList<HabitInfo>
    {
        val habits = ArrayList<HabitInfo>()
        for (habit in profile.habits)
        {
            if (HabitType.valueOf(habit.type!!.name) == habitType) {
                if (!reg.findAll(profile.filter.habitName).any() || habit.name!!.contains(profile.filter.habitName))
                    habits.add(habit)
            }
        }
        if (profile.filter.filterOrdering != FilterOrdering.None && profile.filter.orderingType != OrderingType.None) {
            val filterOrdering = profile.filter.filterOrdering
            if (profile.filter.orderingType == OrderingType.Ascending) {
                habits.sortBy { if (filterOrdering == FilterOrdering.Periodicity) it.periodicity else it.priorityNumeric }
            } else {
                habits.sortByDescending { if (filterOrdering == FilterOrdering.Periodicity) it.periodicity else it.priorityNumeric }
            }
        }

        return habits
    }

    fun loadProfileAsync(function: (HabitsProfile) -> Unit){
        function.invoke(profile)
    }
}