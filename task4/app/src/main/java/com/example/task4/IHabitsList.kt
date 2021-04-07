package com.example.task4

interface IHabitsList {
    var recyclerAdapter: CustomRecyclerAdapter

    fun handleRvClick(item: HabitInfo, pos: Int)
}