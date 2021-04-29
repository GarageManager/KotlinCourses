package com.example.task4.Filter

class HabitFilter {
    var habitName = ""
    var filterOrdering = FilterOrdering.None
    var orderingType = OrderingType.None

    companion object {
        fun default(): HabitFilter
        {
            val habit = HabitFilter()
            habit.habitName = ""
            habit.filterOrdering = FilterOrdering.None
            habit.orderingType = OrderingType.None
            return HabitFilter()
        }
    }

}