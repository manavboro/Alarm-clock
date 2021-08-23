package manabboro.alarmclock.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import manabboro.alarmclock.model.Alarm;

@Dao
public interface AlarmDao {
    @Insert
    void insert(Alarm alarm);

    @Query("DELETE FROM alarm_table")
    void deleteAll();

    @Query("SELECT * FROM alarm_table")
    LiveData<List<Alarm>> getAlarms();

    @Update
    void update(Alarm alarm);
}
