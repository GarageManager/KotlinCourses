package com.example.task4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_habit_editor.*

class HabitEditorFragment : Fragment() {

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
            habit_name.setText(it.getString("habit_name"))
            habit_description.setText(it.getString("habit_description"))
            habit_periodicity.setText(it.getString("habit_periodicity"))
            habit_priority.setSelection(getIndex(habit_priority, it.getString("habit_priority")!!))
        }

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun getIndex(spinner: Spinner, myString: String): Int {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true)) {
                return i
            }
        }
        return 0
    }
}