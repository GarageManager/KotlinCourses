package com.example.task4.Adapters

import  android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task4.Habit.HabitInfo
import com.example.task4.Fragments.IHabitsList
import com.example.task4.R


class CustomRecyclerAdapter(private val values: List<HabitInfo>, private val rv: RecyclerView, private val fragment: IHabitsList) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(), View.OnClickListener {

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.habit_item, parent, false)
        itemView.setOnClickListener(this)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.habitItemName?.text = values[position].name
        holder.habitItemType?.text = "Type: ${values[position].type}"
        holder.habitItemDescription?.text = values[position].description
        holder.habitItemPriority?.text = "Priority: ${values[position].priority}"
        holder.habitItemPeriodicity?.text = "Periodicity: ${values[position].frequency}"
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitItemName: TextView? = itemView.findViewById(R.id.habit_item_name)
        val habitItemDescription: TextView? = itemView.findViewById(R.id.habit_item_description)
        val habitItemPriority: TextView? = itemView.findViewById(R.id.habit_item_priority)
        val habitItemPeriodicity: TextView? = itemView.findViewById(R.id.habit_item_periodicity)
        val habitItemType: TextView? = itemView.findViewById(R.id.habit_item_type)
    }

    override fun onClick(view: View?) {
        val itemPosition: Int? = view?.let { rv.getChildLayoutPosition(it) }
        val item: HabitInfo = values[itemPosition!!]
        fragment.handleRvClick(item)
    }
}