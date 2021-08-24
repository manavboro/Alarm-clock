package manabboro.alarmclock.ui

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import manabboro.alarmclock.R
import manabboro.alarmclock.model.Alarm

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var title: TextView = itemView.findViewById(R.id.textView)
    var toggle: SwitchCompat = itemView.findViewById(R.id.toggle)

    fun bind(alarm: Alarm) {
        title.text = alarm.title
        toggle.isChecked = alarm.isChecked
    }
}