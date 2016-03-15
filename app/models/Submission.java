package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @ManyToOne
    public Course course;

    @ManyToOne
    public Assignment assignment;

    @ManyToOne
    public User student;
}
