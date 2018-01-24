package worldvisionsoft.com.livedataviewholderdemo.repo.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.Posts;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.UserTable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by user on 12/18/2017.
 */

@Dao
public interface DataEntityDao {
    @Insert(onConflict = REPLACE)
    void savePosts(Posts posts);

    @Query("SELECT * FROM Posts WHERE id = :id")
    LiveData<Posts> load(String id);

    @Query("SELECT * FROM Posts")
    LiveData<Posts> getAllPosts();
}
