package manabboro.alarmclock.service

import android.app.*
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import manabboro.alarmclock.R
import manabboro.alarmclock.ui.AlarmActivity

class AlarmService : Service() {

    val CHANNEL_ID = "ALARM_SERVICE_CHANNEL"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val alarmTitle = intent!!.getStringExtra("TITLE")
        val alarmTime = intent.getStringExtra("ALARM_TIME")

        val notificationIntent = Intent(this, AlarmActivity::class.java)
        notificationIntent.putExtra("TITLE", alarmTitle)
        notificationIntent.putExtra("ID", intent.getIntExtra("ID", 0))
        notificationIntent.putExtra("ALARM_TIME", intent.getStringExtra("ALARM_TIME"))

        val pendingIntent =
            PendingIntent.getActivity(this, 0, notificationIntent, FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(CHANNEL_ID, "Alarm")
        }

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(alarmTitle)
            .setContentText(alarmTime)
            .setSmallIcon(R.drawable.ic_stat_access_alarm)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        startForeground(1, notification)
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val channel = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_HIGH
        )
        channel.lightColor = Color.BLUE
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(channel)
        return channelId
    }
}