package com.example.task4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_habits_list.*

class HabitsListFragment : Fragment(), IHabitsList {
    override lateinit var recyclerAdapter: CustomRecyclerAdapter
    override lateinit var habitType: String
    override val habits = ArrayList<HabitInfo>()

    private var callback: ICallBack? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as ICallBack
    }

    companion object {
        fun newInstance(habitType: String): HabitsListFragment {
            val fragment = HabitsListFragment()
            val bundle = Bundle()
            bundle.putString("type", habitType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            habitType = it.getString("type")!!
        }

        for (habit in callback!!.habits)
            if (habit.Type == habitType)
                habits.add(habit)
        recyclerAdapter = CustomRecyclerAdapter(habits, habits_list, this)
        habits_list.layoutManager = LinearLayoutManager(activity)
        habits_list.adapter = recyclerAdapter
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
        bundle.putString("habit_name", item.Name)
        bundle.putString("habit_description", item.Description)
        bundle.putString("habit_priority", item.Priority)
        bundle.putString("habit_periodicity", item.Periodicity)
        bundle.putString("habit_type", item.Type)
        findNavController().navigate(R.id.action_home_to_editor, bundle)
    }
}