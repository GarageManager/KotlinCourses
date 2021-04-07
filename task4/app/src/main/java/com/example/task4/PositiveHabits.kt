package com.example.task4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_positive_habits.*
import java.util.ArrayList

class PositiveHabits : Fragment(), IHabitsList {
    override lateinit var recyclerAdapter: CustomRecyclerAdapter

    private var callback: ICallBack? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as ICallBack
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = CustomRecyclerAdapter(callback!!.positiveHabits, positive_habits, this)
        positive_habits.layoutManager = LinearLayoutManager(activity)
        positive_habits.adapter = recyclerAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_positive_habits, container, false)
    }

    override fun handleRvClick(item: HabitInfo, pos: Int) {
        HabitEditorFragment.newInstance(item, pos)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}