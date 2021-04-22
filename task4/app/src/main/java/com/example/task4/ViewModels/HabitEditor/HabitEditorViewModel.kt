package com.example.task4.ViewModels.HabitEditor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HabitEditorViewModel(private val model: HabitEditorModel) : ViewModel() {
    private val mutableProfile: MutableLiveData<HabitEditorProfile?> = MutableLiveData()
    val profile: LiveData<HabitEditorProfile?> = mutableProfile

    init {
        load()
    }

    private fun load() {
        model.loadProfileAsync { loadedProfile: HabitEditorProfile ->
            mutableProfile.postValue(loadedProfile)
            mutableProfile.value
        }
    }
}