package campa.aracely.mydigimind.ui.dashboard

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import campa.aracely.mydigimind.R
import campa.aracely.mydigimind.databinding.FragmentDashboardBinding
import campa.aracely.mydigimind.ui.Task
import campa.aracely.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.Calendar

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnTime: Button = binding.btnTime
        btnTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                btnTime.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        val btnSave = binding.btnSave
        val etTitulo: EditText = binding.etTitulo
        val checkMonday: CheckBox = binding.checkMonday
        val checkTuesday: CheckBox = binding.checkTuesday
        val checkWednesday: CheckBox = binding.checkWednesday
        val checkThursday: CheckBox = binding.checkThursday
        val checkFriday: CheckBox = binding.checkFriday
        val checkSaturday: CheckBox = binding.checkSaturday
        val checkSunday: CheckBox = binding.checkSunday

        btnSave.setOnClickListener {
            val title = etTitulo.text.toString()
            val time = btnTime.text.toString()
            val days = ArrayList<String>()
            if (checkMonday.isChecked)
                days.add("Monday")
            if (checkTuesday.isChecked)
                days.add("Tuesday")
            if (checkWednesday.isChecked)
                days.add("Wednesday")
            if (checkThursday.isChecked)
                days.add("Thursday")
            if (checkFriday.isChecked)
                days.add("Friday")
            if (checkSaturday.isChecked)
                days.add("Saturday")
            if (checkSunday.isChecked)
                days.add("Sunday")
            val task = Task(title, days, time)

            HomeFragment.tasks.add(task)
            Toast.makeText(root.context, "New task added", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
