package manabboro.alarmclock.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import manabboro.alarmclock.R
import manabboro.alarmclock.ui.RingingActivity

class AlarmService : Service() {

    val CHANNEL_ID = "ALARM_SERVICE_CHANNEL"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, RingingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

//        val alarmTitle = String.format("%s Alarm", intent!!.getStringExtra("TITLE"))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(CHANNEL_ID, "Alarm")
        }

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("alarmTitle")
            .setContentText("Ring Ring .. Ring Ring")
            .setSmallIcon(R.drawable.ic_stat_access_alarm)
            .setContentIntent(pendingIntent)
            .build()

//        mediaPlayer.start()

//        val pattern = longArrayOf(0, 100, 1000)
//        vibrator.vibrate(pattern, 0)

        startForeground(1, notification)
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(
            channelId,
            channelName, NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}