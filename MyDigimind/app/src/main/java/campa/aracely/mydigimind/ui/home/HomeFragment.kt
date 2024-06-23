package campa.aracely.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import campa.aracely.mydigimind.R
import campa.aracely.mydigimind.databinding.FragmentHomeBinding
import campa.aracely.mydigimind.ui.Task

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel
    private var adaptador: AdaptadorTareas? = null

    companion object {
        var tasks = ArrayList<Task>()
        var first = true
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (first) {
            fillTasks()
            first = false
        }



        adaptador = AdaptadorTareas(root.context, tasks)
        val gridView: GridView = root.findViewById(R.id.gridView)
        gridView.adapter = adaptador

        return root
    }

    private fun fillTasks() {
        tasks.add(Task("Practice 1", arrayListOf("Tuesday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday", "Sunday"), "17:40"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"), "14:00"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"), "11:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"), "13:00"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"), "10:40"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"), "12:00"))
    }

    private class AdaptadorTareas(context: Context, tasks: ArrayList<Task>) : BaseAdapter() {
        var tasks = ArrayList<Task>()
        var contexto: Context? = null

        init {
            this.tasks = tasks
            this.contexto = context
        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val task = tasks[position]
            val inflador = LayoutInflater.from(contexto)
            val vista = inflador.inflate(R.layout.task_view, null)
            val tvTitle: TextView = vista.findViewById(R.id.tv_title)
            val tvTime: TextView = vista.findViewById(R.id.tv_time)
            val tvDays: TextView = vista.findViewById(R.id.tv_days)

            tvTitle.text = task.title
            tvTime.text = task.time
            tvDays.text = task.days.joinToString(", ")

            return vista
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

