package com.example.task4.Room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitsDao {
    @Query("SELECT * FROM habits_table")
    fun getAll(): Flow<List<HabitEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg habit: HabitEntity)
    @Delete
    fun delete(feed: HabitEntity)
}