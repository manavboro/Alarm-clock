package manabboro.alarmclock.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import manabboro.alarmclock.R

class AlarmService:Service() {


    override fun onCreate() {
        super.onCreate()
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, RingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val alarmTitle = String.format("%s Alarm", intent!!.getStringExtra(TITLE))

        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(alarmTitle)
            .setContentText("Ring Ring .. Ring Ring")
            .setSmallIcon(R.drawable.ic_alarm_black_24dp)
            .setContentIntent(pendingIntent)
            .build()

        mediaPlayer.start()

        val pattern = longArrayOf(0, 100, 1000)
        vibrator.vibrate(pattern, 0)

        startForeground(1, notification)
        return START_STICKY
    }
}