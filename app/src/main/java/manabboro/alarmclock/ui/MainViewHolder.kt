package manabboro.alarmclock.ui

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import manabboro.alarmclock.R
import manabboro.alarmclock.model.Alarm

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.findViewById(R.id.textView)
    var toggle: SwitchCompat = itemView.findViewById(R.id.toggle)
    var time: TextView = itemView.findViewById(R.id.time)

    fun bind(alarm: Alarm) {
        title.text = alarm.title
        time.text = alarm.formatdate()
        if (alarm.isChecked) {
            time.setTextColor(Color.BLACK)
        } else time.setTextColor(Color.LTGRAY)

        toggle.isChecked = alarm.isChecked
    }
}