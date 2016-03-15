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
@Table(name = "assignments")
public class Assignment extends Model {

    public static Finder<Long, Assignment> find = new Finder<>(Assignment.class);

    @Id
    public Long id;

    @Constraints.Required
    public String description;

    @ManyToOne
    public User lecturer;
}
