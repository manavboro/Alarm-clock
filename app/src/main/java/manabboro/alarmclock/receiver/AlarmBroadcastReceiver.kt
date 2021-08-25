package manabboro.alarmclock.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import manabboro.alarmclock.service.AlarmService

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        startAlarmService(context, intent!!)
    }

    private fun startAlarmService(context: Context?, intent: Intent) {
        val intentService = Intent(context, AlarmService::class.java)

        val data = intent.getStringExtra("TITLE")

        intentService.putExtra("TITLE", data)
        intentService.putExtra("ALARM_TIME", intent.getStringExtra("ALARM_TIME"))
        intentService.putExtra("ID", intent.getIntExtra("ID", 0))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(intentService)
        } else {
            context!!.startService(intentService)
        }
    }
}