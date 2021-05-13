package com.example.task4.Habit

class HabitInfo(val name: String,
                val description: String,
                val priority: HabitPriority,
                val frequency: Int,
                val type: HabitType,
                val count: Int = 0,
                val date: Int = 0,
                val doneDates: List<Int> = listOf(),
                val uid: String = ""
) {
    companion object {
        fun defaultInstance(): HabitInfo =
            HabitInfo("", "", HabitPriority.Low, 0, HabitType.Positive)
    }

    var priorityNumeric = 0

    init {
        priorityNumeric = when (priority) {
            HabitPriority.Low -> 0
            HabitPriority.Medium -> 1
            else -> 2
        }
    }
}