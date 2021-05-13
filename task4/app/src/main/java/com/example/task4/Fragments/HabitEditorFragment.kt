package com.example.task4.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task4.HabitsModel
import com.example.task4.R
import com.example.task4.ViewModels.HabitEditorViewModel
import kotlinx.android.synthetic.main.fragment_habit_editor.*

class HabitEditorFragment : Fragment() {
    private lateinit var editorViewModel: HabitEditorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_habit_editor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewListeners()

        if (arguments != null) {
            arguments?.let {
                setEditorFields(
                    it.getString("name")!!,
                    it.getString("description")!!,
                    it.getInt("periodicity"),
                    it.getString("priority")!!,
                    it.getString("type")!!
                )
                editorViewModel.setUid(it.getString("uid")!!)
                editorViewModel.setDate(it.getInt("date"))
            }
        } else {
            setEditorFields(
                editorViewModel.name.value!!,
                editorViewModel.description.value!!,
                editorViewModel.periodicity.value!!,
                editorViewModel.priority.value!!,
                editorViewModel.type.value!!
            )
        }

        habit_priority.setSelection(0)
        save_habit.setOnClickListener {
            editorViewModel.insert()
            findNavController().popBackStack()
        }
        delete_habit.setOnClickListener{
            editorViewModel.delete()
            findNavController().popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editorViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HabitEditorViewModel(HabitsModel) as T
            }
        }).get(HabitEditorViewModel::class.java)
    }

    private fun setViewListeners()
    {
        setTextViewListener({name: String -> editorViewModel.setName(name)}, habit_name)
        setTextViewListener({description: String -> editorViewModel.setDescription(description)}, habit_description)
        setTextViewListener({periodicity: String -> editorViewModel.setPeriodicity(periodicity)}, habit_periodicity)

        habit_types.setOnCheckedChangeListener { group, checkedId ->
            val checkedRadioButton = group.findViewById<View>(checkedId) as RadioButton
            if (checkedRadioButton.isChecked) {
                editorViewModel.setType(checkedRadioButton.text.toString())
            }
        }

        habit_priority.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                editorViewModel.setPriority(habit_priority.selectedItem.toString())
            }

        }
    }

    private fun setTextViewListener(action: (String) -> Unit, view: TextView)
    {
        view.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    action(s.toString())
                }
            }
        )
    }

    private fun setEditorFields(
        name: String,
        description: String,
        periodicity: Int,
        priority: String,
        type: String
    ) {
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

    private fun radioGroupSetCheck(rg: RadioGroup, myString: String?) {
        rg.check(rg.getChildAt(0).id)
        for (i in 0 until rg.childCount) {
            val rb = activity!!.findViewById<RadioButton>(rg.getChildAt(i).id)
            if (rb.text.toString().equals(myString, ignoreCase = true)) {
                rg.check(rb.id)
            }
        }
    }
}