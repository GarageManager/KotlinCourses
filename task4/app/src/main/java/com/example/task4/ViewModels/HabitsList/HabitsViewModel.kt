package com.example.task4.ViewModels.HabitsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task4.HabitType
import com.example.task4.HabitInfo

class HabitsViewModel(private val model: HabitsModel) : ViewModel() {
    private val mutableProfile: MutableLiveData<HabitsProfile?> = MutableLiveData()
    val profile: LiveData<HabitsProfile?> = mutableProfile

    init {
        load()
    }

    fun getHabits(habitType: HabitType): ArrayList<HabitInfo> = model.getHabits(habitType)

    fun addHabit(habit: HabitInfo)
    {
        profile.value!!.habits.add(habit)
    }

    fun replaceHabit(index: Int, habit: HabitInfo)
    {
        profile.value!!.habits[index] = habit
    }

    private fun load() {
        model.loadProfileAsync { loadedProfile: HabitsProfile ->
            mutableProfile.postValue(loadedProfile)
            mutableProfile.value
        }
    }
}