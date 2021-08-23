package manabboro.alarmclock.database

import android.content.Context
import androidx.lifecycle.LiveData
import manabboro.alarmclock.model.Alarm

class AlarmRepository(context: Context?) {
    private var alarmDao: AlarmDao? = null
    private var alarmsLiveData: LiveData<List<Alarm>>

    init {
        val db: AlarmDatabase = AlarmDatabase.getDatabase(context!!)
        alarmDao = db.alarmDao()
        alarmsLiveData = alarmDao!!.alarms
    }

    fun insert(alarm: Alarm?) {
        AlarmDatabase.databaseWriteExecutor.execute(Runnable { alarmDao!!.insert(alarm) })
    }

    fun update(alarm: Alarm?) {
        AlarmDatabase.databaseWriteExecutor.execute(Runnable { alarmDao!!.update(alarm) })
    }

    fun getAlarmsLiveData(): LiveData<List<Alarm>> {
        return alarmsLiveData
    }

}