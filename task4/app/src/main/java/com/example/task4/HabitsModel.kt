package com.example.task4

import com.example.task4.Filter.HabitFilter
import com.example.task4.Habit.HabitInfo
import com.example.task4.Room.HabitsDao
import com.example.task4.Room.RoomMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object HabitsModel {
    private lateinit var habitsDao: HabitsDao
    lateinit var habits: Flow<List<HabitInfo>>

    fun init(habitsDao: HabitsDao): HabitsModel
    {
        this.habitsDao = habitsDao
        habits = habitsDao.getAll()
            .map { list -> list.map { RoomMapper.habitEntityToHabitInfo(it) } }
        return this
    }

    suspend fun insert(habit: HabitInfo, pos: Int?) {
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