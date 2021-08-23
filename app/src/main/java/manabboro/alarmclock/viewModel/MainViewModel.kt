package manabboro.alarmclock.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import manabboro.alarmclock.database.AlarmRepository
import manabboro.alarmclock.model.Alarm

class MainViewModel constructor(private val mAlarmRepository: AlarmRepository) : ViewModel() {
    var alarmLiveData: LiveData<List<Alarm>> = mAlarmRepository.getAlarmsLiveData()


    fun getAlarmsLiveData(): LiveData<List<Alarm>> {
        return alarmLiveData
    }
}