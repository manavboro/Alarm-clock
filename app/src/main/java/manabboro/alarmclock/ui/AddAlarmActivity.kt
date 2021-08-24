package manabboro.alarmclock.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import manabboro.alarmclock.R
import manabboro.alarmclock.databinding.ActivityAddAlarmBinding
import manabboro.alarmclock.model.Alarm
import java.util.*

class AddAlarmActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityAddAlarmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_alarm)

        mBinding.setAlarm.setOnClickListener {
            schedulerAlarm()
            finish()
        }
    }

    private fun schedulerAlarm() {
        val mTitle = mBinding.titleTextView.text

        val alarm = Alarm()
        alarm.id = System.currentTimeMillis().toInt()
        alarm.created = System.currentTimeMillis()
        alarm.title = mTitle.toString()
        alarm.hour = mBinding.timePicketButton.currentHour
        alarm.minute = mBinding.timePicketButton.currentMinute

        alarm.scheduleAlarm(this)
    }
}