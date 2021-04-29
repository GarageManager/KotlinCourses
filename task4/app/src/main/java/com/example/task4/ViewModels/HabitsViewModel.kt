package com.example.task4.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task4.Filter.HabitFilter
import com.example.task4.Habit.HabitType
import com.example.task4.Habit.HabitInfo
import com.example.task4.HabitsModel

class HabitsViewModel(private val model: HabitsModel) : ViewModel() {
    private val mutableHabits: MutableLiveData<ArrayList<HabitInfo>> = MutableLiveData()
    private val mutableFilter: MutableLiveData<HabitFilter> = MutableLiveData()
    val habits: LiveData<ArrayList<HabitInfo>> = mutableHabits
    val filter: LiveData<HabitFilter> = mutableFilter

    init {
        load()
    }

    fun getHabits(habitType: HabitType): ArrayList<HabitInfo>
    {
        return model.getHabits(habitType, filter.value!!)
    }

    fun addHabit(pos: Int, habit: HabitInfo)
    {
        model.addHabit(pos, habit)
        mutableHabits.notifyObserver()
    }

    fun setFilter(filter: HabitFilter)
    {
        mutableFilter.value = filter
    }

    fun resetFilter()
    {
        mutableFilter.value = HabitFilter.default()
    }

    private fun load() {
        model.loadMain { loadedHabits: ArrayList<HabitInfo>, loadedFilter: HabitFilter ->
            mutableHabits.value = loadedHabits
            mutableFilter.value = loadedFilter
        }
    }

    fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}