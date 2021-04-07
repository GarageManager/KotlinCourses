package com.example.task4

import java.util.ArrayList

interface IHabitsList {
    val recyclerViewItems: ArrayList<HabitInfo>
    var recyclerAdapter: CustomRecyclerAdapter

    fun handleRvClick(item: HabitInfo, pos: Int) {
        HabitEditorFragment.newInstance(item, pos)
    }
}