package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shane on 3/15/16.
 */
@Entity
@Table(name = "assignments")
public class Assignment extends Model {

    public static Finder<Long, Assignment> find = new Finder<>(Assignment.class);

    @Id
    public Long id;

    public String name;

    @Constraints.Required
    public String description;

    @ManyToOne(cascade = CascadeType.ALL)
    public User lecturer;

    @ManyToOne(cascade = CascadeType.ALL)
    public Course course;

    @OneToMany(mappedBy = "assignment")
    public List<Submission> submissions;

    @OneToMany
    public List<Test> tests;

    public Assignment(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
