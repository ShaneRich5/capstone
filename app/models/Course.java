package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Constraint;

/**
 * Created by shane on 3/15/16.
 */
@Entity
@Table(name = "courses")
public class Course extends Model {

    public static Finder<Long, Course> find = new Finder<>(Course.class);

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    // TODO
}
