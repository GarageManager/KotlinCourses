package com.example.task4

import java.util.ArrayList

interface IHabitsList {
    var recyclerAdapter: CustomRecyclerAdapter

    fun handleRvClick(item: HabitInfo, pos: Int) {
        HabitEditorFragment.newInstance(item, pos)
    }
}