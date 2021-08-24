package manabboro.alarmclock.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import manabboro.alarmclock.model.Alarm
import java.util.*
import java.util.concurrent.Executors

@Database(entities = [Alarm::class], version = 2, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao?

    companion object {
        private val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        private var INSTANCE: AlarmDatabase? = null

        fun getDatabase(context: Context): AlarmDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AlarmDatabase::class.java,
                        "alarm_database"
                    ).fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)

                                databaseWriteExecutor.execute {

                                    val calendar = Calendar.getInstance()
                                    calendar.timeInMillis = System.currentTimeMillis()
                                    calendar[Calendar.HOUR_OF_DAY] = 5
                                    calendar[Calendar.MINUTE] = 45
                                    calendar[Calendar.SECOND] = 0
                                    calendar[Calendar.MILLISECOND] = 0

                                    val alarm = Alarm()
                                    alarm.id = System.currentTimeMillis().toInt()
                                    alarm.created = calendar.timeInMillis
                                    alarm.time = calendar.timeInMillis
                                    alarm.title = "Wake me up!!"
                                    alarm.hour = 5
                                    alarm.minute = 45

                                    getDatabase(context).alarmDao()!!.insert(alarm)


                                    //Second item
                                    val calendar2 = Calendar.getInstance()
                                    calendar2.timeInMillis = System.currentTimeMillis()
                                    calendar2[Calendar.HOUR_OF_DAY] = 8
                                    calendar2[Calendar.MINUTE] = 0
                                    calendar2[Calendar.SECOND] = 0
                                    calendar2[Calendar.MILLISECOND] = 0

                                    val alarm2 = Alarm()
                                    alarm2.id = System.currentTimeMillis().toInt()
                                    alarm2.created = calendar2.timeInMillis
                                    alarm2.time = calendar2.timeInMillis
                                    alarm2.title = "Get ready for office"
                                    alarm2.hour = 8
                                    alarm2.minute = 0

                                    getDatabase(context).alarmDao()!!.insert(alarm2)

                                    //Third item
                                    val calendar3 = Calendar.getInstance()
                                    calendar3.timeInMillis = System.currentTimeMillis()
                                    calendar3[Calendar.HOUR_OF_DAY] = 10
                                    calendar3[Calendar.MINUTE] = 30
                                    calendar3[Calendar.SECOND] = 0
                                    calendar3[Calendar.MILLISECOND] = 0

                                    val alarm3 = Alarm()
                                    alarm3.id = System.currentTimeMillis().toInt()
                                    alarm3.created = calendar3.timeInMillis
                                    alarm3.time = calendar3.timeInMillis
                                    alarm3.title = "Meeting"
                                    alarm3.hour = 10
                                    alarm3.minute = 30

                                    getDatabase(context).alarmDao()!!.insert(alarm3)
                                }
                            }
                        })
                        .build()
                }
            }

            return INSTANCE!!
        }

    }
}