package manabboro.alarmclock.database

import androidx.lifecycle.LiveData
import androidx.room.*
import manabboro.alarmclock.model.Alarm

@Dao
interface AlarmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(alarm: Alarm?)

    @Query("DELETE FROM alarm_table")
    fun deleteAll()

    @get:Query("SELECT * FROM alarm_table")
    val alarms: LiveData<List<Alarm>>

    @Query("SELECT * FROM alarm_table WHERE id = :id")
    fun findItem(id: Int): Alarm

    @Update
    fun update(alarm: Alarm?)
}