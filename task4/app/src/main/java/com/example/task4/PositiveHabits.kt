package com.example.task4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_positive_habits.*
import java.util.ArrayList

class PositiveHabits : Fragment(), IHabitsList {
    override val recyclerViewItems = ArrayList<HabitInfo>()
    override lateinit var recyclerAdapter: CustomRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerAdapter = CustomRecyclerAdapter(recyclerViewItems, positive_habits, this)
        positive_habits.adapter = recyclerAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_positive_habits, container, false)
    }
}