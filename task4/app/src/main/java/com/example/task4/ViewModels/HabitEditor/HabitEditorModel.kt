package com.example.task4.ViewModels.HabitEditor

class HabitEditorModel {
    private val profile = HabitEditorProfile()

    fun loadProfileAsync(function: (HabitEditorProfile) -> Unit){
        function.invoke(profile)
    }
}