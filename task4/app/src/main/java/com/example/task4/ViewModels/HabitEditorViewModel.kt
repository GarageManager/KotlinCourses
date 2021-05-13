package com.example.task4.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task4.Habit.HabitInfo
import com.example.task4.Habit.HabitPriority
import com.example.task4.Habit.HabitType
import com.example.task4.HabitsModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HabitEditorViewModel(private val model: HabitsModel) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob() + CoroutineExceptionHandler { _, e -> throw e }

    private val mutableUid: MutableLiveData<String> = MutableLiveData("")
    private val mutableDate: MutableLiveData<Int> = MutableLiveData(0)
    private val mutableName: MutableLiveData<String> = MutableLiveData()
    private val mutableDescription: MutableLiveData<String> = MutableLiveData()
    private val mutableType: MutableLiveData<String> = MutableLiveData()
    private val mutablePeriodicity: MutableLiveData<Int> = MutableLiveData()
    private val mutablePriority: MutableLiveData<String> = MutableLiveData()

    val name: LiveData<String> = mutableName
    val description: LiveData<String> = mutableDescription
    val type: LiveData<String> = mutableType
    val periodicity: LiveData<Int> = mutablePeriodicity
    val priority: LiveData<String> = mutablePriority

    init {
        load()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope
        coroutineContext.cancelChildren()
    }


    fun setUid(uid: String) {
        mutableUid.value = uid
    }

    fun setDate(date: Int) {
        mutableDate.value = date
    }

    fun setName(name: String) {
        mutableName.value = name
    }

    fun setDescription(description: String) {
        mutableDescription.value = description
    }

    fun setType(type: String) {
        mutableType.value = type
    }

    fun setPeriodicity(periodicity: String) {
        mutablePeriodicity.value = if (periodicity == "") 0 else periodicity.toInt()
    }

    fun setPriority(priority: String) {
        mutablePriority.value = priority
    }

    fun insert() {
        launch {
            withContext(Dispatchers.IO) {
                model.insert(getHabit())
            }
        }
    }

    fun delete() {
        launch {
            withContext(Dispatchers.IO) {
                model.delete(getHabit(), mutableUid.value!!)
            }
        }
    }

    private fun getHabit(): HabitInfo {
        return HabitInfo(
            mutableName.value!!,
            mutableDescription.value!!,
            HabitPriority.valueOf(mutablePriority.value!!),
            mutablePeriodicity.value!!.toInt(),
            HabitType.valueOf(mutableType.value!!),
            date = mutableDate.value!!,
            uid = mutableUid.value!!
        )
    }

    private fun load() {
        model.loadEditor { habitInfo: HabitInfo ->
            mutableName.value = habitInfo.name
            mutableDescription.value = habitInfo.description
            mutableType.value = habitInfo.type.toString()
            mutablePeriodicity.value = habitInfo.frequency
            mutablePriority.value = habitInfo.priority.toString()
        }
    }
}