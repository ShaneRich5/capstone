package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import scala.collection.mutable.Seq;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue
    public Long id;

    public String name;

    @ManyToOne
    public Language language;

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

    @NotNull
    public String path;

    public Assignment(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
