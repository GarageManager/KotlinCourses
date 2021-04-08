package com.example.task4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_habit_editor.*

class HabitEditorFragment : Fragment() {

    private var callback: ICallBack? = null
    private var position: Int = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as ICallBack
    }

    companion object {
        fun newInstance(habitInfo: HabitInfo, pos: Int): HabitEditorFragment {
            val fragment = HabitEditorFragment()
            val bundle = Bundle()
            bundle.putInt("item_position", pos)
            bundle.putString("habit_name", habitInfo.Name)
            bundle.putString("habit_description", habitInfo.Description)
            bundle.putString("habit_priority", habitInfo.Priority)
            bundle.putString("habit_periodicity", habitInfo.Periodicity)
            bundle.putString("habit_type", habitInfo.Type)
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

        arguments?.let {
            position = it.getInt("item_position")
            habit_name.setText(it.getString("habit_name"))
            habit_description.setText(it.getString("habit_description"))
            habit_periodicity.setText(it.getString("habit_periodicity"))
            habit_priority.setSelection(getSpinnerIndex(habit_priority, it.getString("habit_priority")!!))
            radioGroupSetCheck(habit_types, it.getString("habit_type"))
        }

        view.findViewById<Button>(R.id.save_habit).setOnClickListener {
            addHabit()
            findNavController().navigate(R.id.action_editor_to_home)
        }
    }

    private fun getSpinnerIndex(spinner: Spinner, myString: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true))
                return i
        }
        return 0
    }

    private fun radioGroupSetCheck(rg: RadioGroup, myString: String?){
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
            habit_periodicity.text.toString(),
            habitType
        )
        if (position == -1)
            callback!!.habits.add(habit)
        else
            callback!!.habits[position] = habit
    }
}