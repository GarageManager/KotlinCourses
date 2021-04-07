package com.example.task4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_negative_habits.*

class NegativeHabits : Fragment(), IHabitsList {
    override lateinit var recyclerAdapter: CustomRecyclerAdapter

    private var callback: ICallBack? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as ICallBack
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = CustomRecyclerAdapter(callback!!.negativeHabits, negative_habits, this)
        negative_habits.layoutManager = LinearLayoutManager(activity)
        negative_habits.adapter = recyclerAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_negative_habits, container, false)
    }

    override fun handleRvClick(item: HabitInfo, pos: Int) {
        HabitEditorFragment.newInstance(item, pos)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

}