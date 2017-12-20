package worldvisionsoft.com.livedataviewholderdemo.repo.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import worldvisionsoft.com.livedataviewholderdemo.repo.local.dao.DataEntityDao;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.UserTable;

/**
 * Created by user on 12/18/2017.
 */

@Database(entities = {UserTable.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract DataEntityDao userDao();
}
