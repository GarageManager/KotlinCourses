package com.example.task4.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.example.task4.Filter.FilterOrdering
import com.example.task4.Filter.HabitFilter
import com.example.task4.Filter.OrderingType
import com.example.task4.Habit.HabitType
import com.example.task4.Habit.HabitInfo
import com.example.task4.HabitsModel
import com.example.task4.Room.RoomMapper

class HabitsViewModel(private val model: HabitsModel) : ViewModel() {
    private val mutableFilter: MutableLiveData<HabitFilter> = MutableLiveData()
    private val reg = "[^\\w]*(\\w+)[^\\w]*".toRegex()
    val habits: LiveData<MutableList<HabitInfo>> = model.habits.asLiveData()
        .map {arrayList -> arrayList.map { RoomMapper.habitEntityToHabitInfo(it) }.toMutableList() }
    val filter: LiveData<HabitFilter> = mutableFilter

    init {
        load()
    }

    fun getHabits(habitType: HabitType): ArrayList<HabitInfo> {
        val filteredHabits = ArrayList<HabitInfo>()
        if (habits.value == null)
            return filteredHabits
        for (habit in habits.value!!) {
            if (habit.type == habitType) {
                if (!reg.findAll(filter.value!!.habitName).any() || habit.name.contains(filter.value!!.habitName))
                    filteredHabits.add(habit)
            }
        }
        if (filter.value!!.filterOrdering != FilterOrdering.None) {
            val filterOrdering = filter.value!!.filterOrdering
            if (filter.value!!.orderingType == OrderingType.Ascending) {
                filteredHabits.sortBy { if (filterOrdering == FilterOrdering.Periodicity) it.periodicity else it.priorityNumeric }
            } else if (filter.value!!.orderingType == OrderingType.Descending) {
                filteredHabits.sortByDescending { if (filterOrdering == FilterOrdering.Periodicity) it.periodicity else it.priorityNumeric }
            }
        }

        return filteredHabits
    }

    fun setFilter(filter: HabitFilter) {
        this.mutableFilter.value = filter
    }

    fun resetFilter() {
        mutableFilter.value = HabitFilter.default()
    }

    private fun load() {
        model.loadMain { loadedFilter: HabitFilter ->
            mutableFilter.value = loadedFilter
        }
    }
}