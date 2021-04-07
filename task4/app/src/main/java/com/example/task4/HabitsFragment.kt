package com.example.task4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HabitsFragment : Fragment() {

    private var callback: ICallBack? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = activity as ICallBack
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = view.findViewById<ViewPager2>(R.id.pager)
        viewPager.adapter = HabitsPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Positive"
                else -> "Negative"
            }
        }.attach()



            view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//                val fragment = HabitEditorFragment()
//                activity!!.supportFragmentManager.beginTransaction()
//                    .replace(id, fragment, "findThisFragment")
//                    .addToBackStack(null)
//                    .commit()
        }
    }


}