package com.example.task4.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task4.Habit.HabitInfo
import com.example.task4.Habit.HabitPriority
import com.example.task4.Habit.HabitType
import com.example.task4.HabitsModel

class HabitEditorViewModel(private val model: HabitsModel) : ViewModel() {
    private val mutableName: MutableLiveData<String> = MutableLiveData()
    private val mutableDescription: MutableLiveData<String> = MutableLiveData()
    private val mutableType: MutableLiveData<String> = MutableLiveData()
    private val mutablePeriodicity: MutableLiveData<Int> = MutableLiveData()
    private val mutablePriority: MutableLiveData<String> = MutableLiveData()
    private val mutablePosition: MutableLiveData<Int> = MutableLiveData()

    val name: LiveData<String> = mutableName
    val description: LiveData<String> = mutableDescription
    val type: LiveData<String> = mutableType
    val periodicity: LiveData<Int> = mutablePeriodicity
    val priority: LiveData<String> = mutablePriority
    val position: LiveData<Int> = mutablePosition

    init {
        load()
    }

    fun setName(name: String) { mutableName.value = name }
    fun setDescription(description: String) { mutableDescription.value = description }
    fun setType(type: String) { mutableType.value = type }
    fun setPeriodicity(periodicity: String) { mutablePeriodicity.value = if (periodicity == "") 0 else periodicity.toInt() }
    fun setPriority(priority: String) { mutablePriority.value = priority }
    fun setPosition(position: Int) {mutablePosition.value = position }

    fun getHabit(): HabitInfo{
        return HabitInfo(
            mutableName.value,
            mutableDescription.value,
            HabitPriority.valueOf(mutablePriority.value!!),
            mutablePeriodicity.value!!.toInt(),
            HabitType.valueOf(mutableType.value!!)
        )
    }

    private fun load() {
        model.loadEditor { habitInfo: HabitInfo ->
            mutableName.value = habitInfo.name
            mutableDescription.value = habitInfo.description
            mutableType.value = habitInfo.type.toString()
            mutablePeriodicity.value = habitInfo.periodicity
            mutablePriority.value = habitInfo.priority.toString()
        }
    }
}