package manabboro.alarmclock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import manabboro.alarmclock.database.AlarmRepository
import manabboro.alarmclock.viewModel.MainViewModel

class ViewModelFactory {

    companion object {
        class MainViewModelFactory constructor(private val alarmRepository: AlarmRepository) : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    return MainViewModel(alarmRepository) as T
                }
                throw IllegalArgumentException("UnknownViewModel")
            }

        }
    }
}