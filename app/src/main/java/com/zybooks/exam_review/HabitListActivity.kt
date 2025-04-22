import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zybooks.exam_review.R
import com.zybooks.exam_review.HabitAdapter
import com.zybooks.exam_review.Habit

class HabitListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = HabitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_list)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val habits = listOf(
            Habit("Drink Water", "08:00", "8 cups/day"),
            Habit("Read Book", "21:00", "30 mins/day"),
            Habit("Exercise", "07:30", "15 mins")
        )

        adapter.submitList(habits)
    }
}