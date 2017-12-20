package worldvisionsoft.com.livedataviewholderdemo.repo.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by user on 12/18/2017.
 */
@Entity(tableName = "users")
public class UserTable {

    @PrimaryKey
    private int id;

    private String name;

    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
