package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Shane on 5/17/2016.
 */
@Entity
@Table(name = "tokens")
public class Token extends Model {

    public static Finder<Long, Token> find = new Finder<>(Token.class);

    @Id
    public int id;

    @Constraints.Required
    public String value;

    public String description;

    @ManyToOne
    public User user;
}
