package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by uhhh on 5/16/16.
 */
@Entity
@Table(name = "languages")
public class Language extends Model
{
    public static Model.Finder<Long, Language> find = new Finder<>(Language.class);

    @Id
    @Constraints.Required
    @Column(unique = true)
    private String name;

    @ManyToOne
    private List<Assignment> assignments;

    public Language(String name)
    {
        this.name = name;
        assignments = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}
