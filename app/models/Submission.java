package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;

/**
 * Created by shane on 3/15/16.
 */
@Entity
@Table(name = "submissions")
public class Submission extends Model {

    public static Finder<Long, Submission> find = new Finder<>(Submission.class);

    @Id
    public Long id;

    public double grade;

    @Constraints.Required
    public String path;

    /*
    Relationship
     */
    @ManyToOne(cascade = CascadeType.ALL)
    public Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    public Assignment assignment;

    @ManyToOne(cascade = CascadeType.ALL)
    public User student;
}
