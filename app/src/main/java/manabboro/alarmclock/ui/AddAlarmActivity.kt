package manabboro.alarmclock.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import manabboro.alarmclock.R
import manabboro.alarmclock.database.AlarmRepository
import manabboro.alarmclock.databinding.ActivityAddAlarmBinding
import manabboro.alarmclock.model.Alarm
import manabboro.alarmclock.receiver.AlarmBroadcastReceiver
import java.util.*

class AddAlarmActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityAddAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_alarm)

        mBinding.setAlarm.setOnClickListener {
            schedulerAlarm()
        }
    }

    private fun schedulerAlarm() {
        val alarmId = System.currentTimeMillis().toInt()
        val alarm = Alarm("")

        //1. insert to database
        val alarmRepository = AlarmRepository(this)
        alarmRepository.insert(alarm)

        //2. scheduler alarm
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        val alarmPendingIntent = PendingIntent.getBroadcast(this, alarmId, intent, 0)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = 1
        calendar[Calendar.MINUTE] = 1
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0


        // if alarm time has already passed, increment day by 1
        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar[Calendar.DAY_OF_MONTH] = calendar[Calendar.DAY_OF_MONTH] + 1
        }

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
}