package com.example.task4.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits_table")
data class HabitEntity(@ColumnInfo(name = "name") val name: String,
                       @ColumnInfo(name = "description") val description: String,
                       @ColumnInfo(name = "type") val type: String,
                       @ColumnInfo(name = "periodicity") val periodicity: String,
                       @ColumnInfo(name = "priority") val priority: String
)
{
    @PrimaryKey(autoGenerate = true) var id = 0
}