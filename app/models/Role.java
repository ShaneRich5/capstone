package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by shane on 3/15/16.
 */
@Entity
@Table(name = "roles")
public class Role extends Model {

    public static Finder<Long, Role> find = new Finder<>(Role.class);

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    public String description;


    /*
        Relationships
     */
    @OneToOne(mappedBy = "role")
    public User user;

}
