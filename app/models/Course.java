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

    @Column(unique = true)
    @Constraints.Required
    public String name;

    public String description;

    @Id
    @Constraints.Required
    @Column(unique = true)
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

    public void addParticipant(User participant){participants.add(participant);}

    public static String codeFromName(String name)
    {
        return name.substring(0,8);
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
