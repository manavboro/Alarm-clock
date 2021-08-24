package manabboro.alarmclock.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import manabboro.alarmclock.service.AlarmService

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent!!.action) {
//            val toastText = String.format("Alarm Reboot")
//            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
//            startRescheduleAlarmsService(context)
        } else {
            val toastText = String.format("Alarm Received")
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
            startAlarmService(context, intent)
        }
    }

    private fun startAlarmService(context: Context?, intent: Intent) {
        val intentService = Intent(context, AlarmService::class.java)

        val data = intent.getStringExtra("TITLE")

        intentService.putExtra("TITLE", "")
        intentService.putExtra("ID", intent.getIntExtra("ID", 0))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(intentService)
        } else {
            context!!.startService(intentService)
        }
    }
}