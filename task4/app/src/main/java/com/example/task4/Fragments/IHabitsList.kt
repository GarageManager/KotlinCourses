package com.example.task4.Fragments

import com.example.task4.HabitInfo

interface IHabitsList {
    fun handleRvClick(item: HabitInfo, pos: Int)
}