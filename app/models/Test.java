package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by uhhh on 5/16/16.
 */
@Entity
@Table(name = "tests")
public class Test extends Model {

    public String testHeader;

    @OneToMany
    public List<TestCase> testCases;

    @ManyToOne(cascade = CascadeType.ALL)
    public Assignment assignment;
}
