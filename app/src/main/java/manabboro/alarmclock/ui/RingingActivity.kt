package manabboro.alarmclock.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import manabboro.alarmclock.R
import manabboro.alarmclock.service.AlarmService

class RingingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ringing)

        findViewById<Button>(R.id.button).setOnClickListener {
            val intentService = Intent(applicationContext, AlarmService::class.java)
            applicationContext.stopService(intentService)
            finish()
        }
    }
}