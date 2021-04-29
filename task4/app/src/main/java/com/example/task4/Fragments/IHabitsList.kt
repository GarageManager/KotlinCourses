package com.example.task4.Fragments

import com.example.task4.Habit.HabitInfo

interface IHabitsList {
    fun handleRvClick(item: HabitInfo, pos: Int)
}