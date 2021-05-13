package com.example.task4.Room

import com.example.task4.Habit.HabitInfo
import com.example.task4.Habit.HabitPriority
import com.example.task4.Habit.HabitType

object RoomMapper {
    fun habitInfoToHabitEntity(habitInfo: HabitInfo): HabitEntity {
        return HabitEntity(
            habitInfo.name,
            habitInfo.description,
            habitInfo.type.name,
            habitInfo.frequency,
            habitInfo.priority.name,
            habitInfo.count,
            habitInfo.date,
            habitInfo.doneDates,
            habitInfo.uid
        )
    }

    fun habitEntityToHabitInfo(habitEntity: HabitEntity): HabitInfo {
        return HabitInfo(
            habitEntity.name,
            habitEntity.description,
            HabitPriority.valueOf(habitEntity.priority),
            habitEntity.frequency,
            HabitType.valueOf(habitEntity.type),
            habitEntity.count,
            habitEntity.day,
            habitEntity.doneDates,
            habitEntity.uid
        )
    }
}