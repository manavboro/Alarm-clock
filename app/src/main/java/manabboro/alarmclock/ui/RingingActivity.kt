package manabboro.alarmclock.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import manabboro.alarmclock.R
import manabboro.alarmclock.database.AlarmRepository
import manabboro.alarmclock.service.AlarmService

class RingingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ringing)

        val title = intent.getStringExtra("TITLE")
        val id = intent.getIntExtra("ID", 0)

        findViewById<TextView>(R.id.textView2).text = title

        findViewById<Button>(R.id.button).setOnClickListener {
            val intentService = Intent(applicationContext, AlarmService::class.java)
            applicationContext.stopService(intentService)

            val alarmRepository = AlarmRepository(this)
            val alarm = alarmRepository.findItem(id)
            alarm.isChecked = false
            alarmRepository.update(alarm)
            finish()
        }
    }
}