package com.example.task4.Network

import com.example.task4.Habit.HabitPriority
import com.example.task4.Habit.HabitType
import com.example.task4.Room.HabitEntity
import com.google.gson.*
import java.lang.reflect.Type

class HabitEntityJsonSerializer: JsonSerializer<HabitEntity> {
    override fun serialize(src: HabitEntity?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement =
        JsonObject().apply {
            addProperty("color", 0)
            addProperty("count", src?.count)
            addProperty("date", src?.day?.plus(1))
            addProperty("description", src?.description)
            add("done_dates", listToJson(src?.doneDates))
            addProperty("frequency", src?.frequency)
            addProperty("priority", src?.priority?.let { priorityToInt(it) })
            addProperty("title", src?.name)
            addProperty("type", src?.type?.let { typeToInt(it) })
            addProperty("uid", src?.uid)
    }

    private fun listToJson(list: List<Int>?): JsonArray {
        val jsonArray = JsonArray()
        list?.forEach { jsonArray.add(it) }
        return jsonArray
    }

    private fun priorityToInt(priority: String): Int = when(priority){
        HabitPriority.Low.name -> 0
        HabitPriority.Medium.name -> 1
        else -> 2
    }

    private fun typeToInt(type: String): Int = when(type){
        HabitType.Negative.name -> 0
        else -> 1
    }
}