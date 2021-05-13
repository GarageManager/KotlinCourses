package com.example.task4.Room

import androidx.room.*

@Entity(tableName = "habits_table")
@TypeConverters(ListConverter::class)
data class HabitEntity(@ColumnInfo(name = "name") val name: String,
                       @ColumnInfo(name = "description") val description: String,
                       @ColumnInfo(name = "type") val type: String,
                       @ColumnInfo(name = "frequency") val frequency: Int,
                       @ColumnInfo(name = "priority") val priority: String,
                       @ColumnInfo(name = "count") val count: Int,
                       @ColumnInfo(name = "day") val day: Int,
                       @ColumnInfo(name = "doneDates")  val doneDates: List<Int> = listOf(),
                       @PrimaryKey val uid: String)