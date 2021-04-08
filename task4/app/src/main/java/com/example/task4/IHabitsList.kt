package com.example.task4

interface IHabitsList {
    var recyclerAdapter: CustomRecyclerAdapter

    var habitType: String

    val habits: ArrayList<HabitInfo>

    fun handleRvClick(item: HabitInfo, pos: Int)
}