package worldvisionsoft.com.livedataviewholderdemo.repo.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.UserTable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by user on 12/18/2017.
 */

@Dao
public interface DataEntityDao {
    @Insert(onConflict = REPLACE)
    void save(UserTable user);

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<UserTable> load(String userId);

    @Query("SELECT * FROM user")
    LiveData<List<UserTable>> getAllUsers();
}
