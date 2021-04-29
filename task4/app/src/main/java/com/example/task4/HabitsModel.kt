package com.example.task4

import com.example.task4.Filter.HabitFilter
import com.example.task4.Habit.HabitInfo
import com.example.task4.Room.HabitEntity
import com.example.task4.Room.HabitsDao
import com.example.task4.Room.RoomMapper
import kotlinx.coroutines.flow.Flow

object HabitsModel {
    private lateinit var habitsDao: HabitsDao
    lateinit var habits: Flow<List<HabitEntity>>

    fun init(habitsDao: HabitsDao): HabitsModel
    {
        this.habitsDao = habitsDao
        habits = habitsDao.getAll()
        return this
    }

    fun insert(habit: HabitInfo, pos: Int?) {
        habitsDao.insert(RoomMapper.habitInfoToHabitEntity(habit, pos))
    }


    fun loadMain(function: (HabitFilter) -> Unit){
        function.invoke(HabitFilter())
    }

    fun loadEditor(function: (HabitInfo) -> Unit)
    {
        function.invoke(HabitInfo.defaultInstance())
    }
}