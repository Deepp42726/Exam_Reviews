package com.zybooks.exam_review


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zybooks.exam_review.R
import com.zybooks.exam_review.HabitAdapter
import com.zybooks.exam_review.Habit

class HabitListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = HabitAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_habit_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val habits = listOf(
            Habit("Drink Water", "08:00", "8 cups/day"),
            Habit("Read Book", "21:00", "30 mins/day"),
            Habit("Exercise", "07:30", "15 mins")
        )

        adapter.submitList(habits)

        view.findViewById<Button>(R.id.btnAddHabit).setOnClickListener {
            findNavController().navigate(R.id.action_habitListFragment_to_addHabitFragment)
        }

        view.findViewById<Button>(R.id.btnShowQuote).setOnClickListener {
            findNavController().navigate(R.id.action_habitListFragment_to_quoteFragment)
        }
    }
}

