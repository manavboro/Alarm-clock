package manabboro.alarmclock.model

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Entity
import androidx.room.PrimaryKey
import manabboro.alarmclock.database.AlarmRepository
import manabboro.alarmclock.receiver.AlarmBroadcastReceiver
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "alarm_table")
class Alarm {


    @PrimaryKey
    var id: Int = 0
    var created: Long = 0
    var title: String = ""
    var hour: Int = 0
    var minute: Int = 0
    var time: Long = 0
    var isChecked: Boolean = false


    fun toggleAlarm(checked: Boolean, context: Context) {
        if (checked) scheduleAlarm(context) else cancelAlarm(context)
    }

    fun scheduleAlarm(context: Context) {
        isChecked = true

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = hour
        calendar[Calendar.MINUTE] = minute
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0

        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar[Calendar.DAY_OF_MONTH] = calendar[Calendar.DAY_OF_MONTH] + 1
        }

        time = calendar.timeInMillis

        val alarmRepository = AlarmRepository(context)
        alarmRepository.insert(this)


        val alarmManager = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        intent.putExtra("TITLE", title)
        intent.putExtra("ID", id)

        val alarmPendingIntent = PendingIntent.getBroadcast(context, id, intent, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                alarmPendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                alarmPendingIntent
            )
        }
    }

    fun cancelAlarm(context: Context) {
        this.isChecked = false

        val alarmRepository = AlarmRepository(context)
        alarmRepository.update(this)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        val alarmPendingIntent = PendingIntent.getBroadcast(context, id, intent, 0)
        alarmManager.cancel(alarmPendingIntent)
    }

    fun formatdate(): String {
        val date = Date(time)
        val formatter = SimpleDateFormat("hh:mm a");
       return formatter.format(date);
    }
}
