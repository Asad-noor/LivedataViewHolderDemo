package worldvisionsoft.com.livedataviewholderdemo.repo.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by user on 12/18/2017.
 */
@Entity
public class UserTable {
    @PrimaryKey
    private int id;
    private String name;
    private String lastName;
}
