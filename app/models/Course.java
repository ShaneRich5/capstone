package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by shane on 3/15/16.
 */
@Entity
@Table(name = "courses")
public class Course extends Model {

    public static Finder<Long, Course> find = new Finder<Long, Course>(Course.class);

    @Id
    public Long id;

    @Column(unique = true)
    @Constraints.Required
    public String name;

    public String description;

    public String code;

    @OneToOne
    public User lecturer;

    @ManyToMany
    public List<User> participants;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assignment> assignments;

    public Course(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public static String codeFromName(String name)
    {
        return name.substring(8);
    }

    public static Course findByName(String name) {
        return Course.find.where()
                .eq("name", name)
                .findUnique();
    }
    public static Course findByCode(String code) {
        return Course.find.where()
                .eq("code", code)
                .findUnique();
    }
}
