package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Created by shane on 3/15/16.
 */
@Entity
@Table(name = "roles")
public class Role extends Model {
    public static Model.Finder<Long, Role> find = new Model.Finder<Long, Role>(Role.class);

    @Id
    public int id;

    @Constraints.Required
    public String name;

    public String description;


    /*
        Relationships
     */
    @OneToMany(mappedBy = "role")
    public User user;

    public Role(int id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
