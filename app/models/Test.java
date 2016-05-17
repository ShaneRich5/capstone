package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by uhhh on 5/16/16.
 */
@Entity
@Table(name = "tests")
public class Test extends Model
{
    public static Finder<Long, Test> find = new Finder<>(Test.class);
    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
    public long id;

    @javax.persistence.Column(length=10000)
    public String fullTest ;

    @OneToMany
    private List<TestCase> testCases;

    @ManyToOne
    public Assignment assignment;


    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public long getId() {
        return id;
    }

}
