package com.example.task4.Room

import com.example.task4.Habit.HabitInfo
import com.example.task4.Habit.HabitPriority
import com.example.task4.Habit.HabitType

object RoomMapper {
    fun habitInfoToHabitEntity(habitInfo: HabitInfo, pos: Int?): HabitEntity {
        val entity = HabitEntity(
            habitInfo.name,
            habitInfo.description,
            habitInfo.type.name,
            habitInfo.periodicity.toString(),
            habitInfo.priority.name
        )
        if (pos != null)
            entity.id = pos + 1
        return entity
    }

    fun habitEntityToHabitInfo(habitEntity: HabitEntity): HabitInfo {
        return HabitInfo(
            habitEntity.name,
            habitEntity.description,
            HabitPriority.valueOf(habitEntity.priority),
            habitEntity.periodicity.toInt(),
            HabitType.valueOf(habitEntity.type)
        )
    }
}