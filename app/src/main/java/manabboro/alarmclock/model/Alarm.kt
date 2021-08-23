package manabboro.alarmclock.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm_table")
class Alarm(val title: String, val time: Long) {

    @PrimaryKey(autoGenerate = true)
     var id: Int=0

// fun cancelAlarm(context: Context) {
//  val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//  val intent = Intent(context, AlarmBroadcastReceiver::class.java)
//  val alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0)
//  alarmManager.cancel(alarmPendingIntent)
//  this.started = false
//  val toastText = String.format("Alarm cancelled for %02d:%02d with id %d", hour, minute, alarmId)
//  Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
//  Log.i("cancel", toastText)
// }

}
