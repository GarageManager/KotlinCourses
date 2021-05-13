package com.example.task4.Network

import com.example.task4.Habit.HabitPriority
import com.example.task4.Habit.HabitType
import com.example.task4.Room.HabitEntity
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class HabitEntityJsonDeserializer: JsonDeserializer<HabitEntity> {
    override fun deserialize(
        json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?
    ): HabitEntity = HabitEntity(
            json.asJsonObject.get("title").asString,
            json.asJsonObject.get("description").asString,
            integerTypeToString(json.asJsonObject.get("type").asInt),
            json.asJsonObject.get("frequency").asInt,
            integerPriorityToString(json.asJsonObject.get("priority").asInt),
            json.asJsonObject.get("count").asInt,
            json.asJsonObject.get("date").asInt,
            jsonToList(json.asJsonObject.get("done_dates").asJsonArray),
            json.asJsonObject.get("uid").asString
        )

    private fun jsonToList(json: JsonArray): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(json, listType)
    }

    private fun integerPriorityToString(priority: Int): String = when(priority){
            0 -> HabitPriority.Low.name
            1 -> HabitPriority.Medium.name
            else -> HabitPriority.High.name
    }

    private fun integerTypeToString(type: Int): String = when(type){
        0 -> HabitType.Negative.name
        else -> HabitType.Positive.name
    }
}