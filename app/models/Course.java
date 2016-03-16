package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

/**
 * Created by shane on 3/15/16.
 */
@Entity
@Table(name = "courses")
public class Course extends Model {

    public static Finder<Long, Course> find = new Finder<>(Course.class);

    @Id
    public Long id;

    @Column(unique = true)
    @Constraints.Required
    public String name;

    public String description;


    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @ManyToMany
    public List<User> participants;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assignment> assignments;
}
