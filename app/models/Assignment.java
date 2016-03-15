package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by shane on 3/15/16.
 */
@Entity
@Table(name = "assignments")
public class Assignment extends Model {

    @Id
    public Long id;
}
