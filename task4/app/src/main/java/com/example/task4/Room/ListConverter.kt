package com.example.task4.Room

import androidx.room.TypeConverter

class ListConverter {
    @TypeConverter
    fun fromList(list: List<Int>): String {
        return list.joinToString { ", " }
    }

    @TypeConverter
    fun toList(data: String): List<Int>{
        if (data == "")
            return listOf()
        return data.split(", ").map { it.toInt() }
    }
}