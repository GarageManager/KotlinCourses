package com.example.task4.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task4.*
import com.example.task4.Adapters.CustomRecyclerAdapter
import com.example.task4.ViewModels.HabitsList.HabitsModel
import com.example.task4.ViewModels.HabitsList.HabitsViewModel
import kotlinx.android.synthetic.main.fragment_habits_list.*

class HabitsListFragment : Fragment(), IHabitsList {
    private lateinit var recyclerAdapter: CustomRecyclerAdapter
    private lateinit var habitType: HabitType
    private val viewModel: HabitsViewModel by activityViewModels()

    companion object {
        fun newInstance(type: HabitType): HabitsListFragment {
            val fragment = HabitsListFragment()
            val bundle = Bundle()
            bundle.putSerializable("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            habitType = it.getSerializable("type") as HabitType
        }

        viewModel.profile.observe(viewLifecycleOwner, Observer { profile ->
            val habits = viewModel.getHabits(habitType)
            recyclerAdapter = CustomRecyclerAdapter(habits, habits_list, this)
            habits_list.layoutManager = LinearLayoutManager(activity)
            habits_list.adapter = recyclerAdapter
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_habits_list, container, false)
    }

    override fun handleRvClick(item: HabitInfo, pos: Int) {
        HabitEditorFragment.newInstance(item, pos)
        val bundle = Bundle()
        bundle.putInt("item_position", pos)
        bundle.putString("habit_name", item.name)
        bundle.putString("habit_description", item.description)
        bundle.putString("habit_priority", item.priority)
        bundle.putInt("habit_periodicity", item.periodicity)
        bundle.putSerializable("habit_type", item.type)
        findNavController().navigate(R.id.action_home_to_editor, bundle)
    }
}