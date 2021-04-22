package com.example.task4.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task4.HabitInfo
import com.example.task4.HabitType
import com.example.task4.R
import com.example.task4.ViewModels.HabitEditor.HabitEditorModel
import com.example.task4.ViewModels.HabitEditor.HabitEditorViewModel
import com.example.task4.ViewModels.HabitsList.HabitsViewModel
import kotlinx.android.synthetic.main.fragment_habit_editor.*

class HabitEditorFragment : Fragment() {
    private val habitsViewModel: HabitsViewModel by activityViewModels()
    private lateinit var editorViewModel: HabitEditorViewModel
    private var position: Int = -1

    companion object {
        fun newInstance(habitInfo: HabitInfo, pos: Int): HabitEditorFragment {
            val fragment = HabitEditorFragment()
            val bundle = Bundle()
            bundle.putInt("item_position", pos)
            bundle.putString("habit_name", habitInfo.name)
            bundle.putString("habit_description", habitInfo.description)
            bundle.putString("habit_priority", habitInfo.priority)
            bundle.putInt("habit_periodicity", habitInfo.periodicity)
            bundle.putSerializable("habit_type", habitInfo.type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_habit_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            arguments?.let {
                setEditorFields(
                    it.getString("habit_name")!!,
                    it.getString("habit_description")!!,
                    it.getInt("habit_periodicity"),
                    it.getString("habit_priority")!!,
                    (it.getSerializable("habit_type") as HabitType).name,
                    it.getInt("item_position")
                )
            }
        } else if(editorViewModel.profile.value != null) {
            val profile = editorViewModel.profile.value!!
            setEditorFields(
                profile.name,
                profile.description,
                profile.periodicity,
                profile.priority,
                profile.type.name,
                profile.position
            )
        }

        habit_priority.setSelection(0)
        save_habit.setOnClickListener {
            addHabit()
            findNavController().popBackStack(R.id.nav_home, false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editorViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HabitEditorViewModel(HabitEditorModel()) as T
            }
        }).get(HabitEditorViewModel::class.java)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val profile = editorViewModel.profile.value!!
        profile.name = habit_name.text.toString()
        profile.description = habit_description.text.toString()
        profile.periodicity = habit_periodicity.text.toString().toInt()
        profile.type =
            HabitType.valueOf(activity?.findViewById<RadioButton>(habit_types.checkedRadioButtonId)?.text.toString())
        profile.priority = habit_priority.selectedItem.toString()
        profile.position = position
        super.onSaveInstanceState(outState)
    }

    private fun radioGroupSetCheck(rg: RadioGroup, myString: String?) {
        rg.check(rg.getChildAt(0).id)
        for (i in 0 until rg.childCount) {
            val rb = activity!!.findViewById<RadioButton>(rg.getChildAt(i).id)
            if (rb.text.toString().equals(myString, ignoreCase = true)) {
                rg.check(rb.id)
            }
        }
    }

    private fun addHabit() {
        val habitType = activity?.findViewById<RadioButton>(habit_types.checkedRadioButtonId)?.text.toString()
        val habit = HabitInfo(
            habit_name.text.toString(),
            habit_description.text.toString(),
            habit_priority.selectedItem.toString(),
            habit_periodicity.text.toString().toInt(),
            HabitType.valueOf(habitType)
        )
        if (position == -1)
            habitsViewModel.addHabit(habit)
        else
            habitsViewModel.replaceHabit(position, habit)
    }

    private fun setEditorFields(
        name: String,
        description: String,
        periodicity: Int,
        priority: String,
        type: String,
        pos: Int
    ) {
        position = pos
        habit_name.setText(name)
        habit_description.setText(description)
        habit_periodicity.setText(periodicity.toString())
        habit_priority.setSelection(getSpinnerIndex(habit_priority, priority))
        radioGroupSetCheck(habit_types, type)
    }

    private fun getSpinnerIndex(spinner: Spinner, myString: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true))
                return i
        }
        return 0
    }
}