package com.example.task4

class HabitInfo(val name: String?,
                val description: String?,
                val priority: String?,
                val periodicity: Int,
                val type: HabitType?
)
{
    var priorityNumeric= 0
    init {
        priorityNumeric = when (priority)
        {
            "Low" -> 0
            "Medium" -> 1
            else -> 2
        }
    }
}