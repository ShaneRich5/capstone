package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by uhhh on 5/16/16.
 */
@Entity
@Table(name = "tests")
public class Test extends Model
{
    public static Finder<Long, Test> find = new Finder<>(Test.class);
    @Id
    private int id;

    @OneToMany
    private List<TestCase> testCases;

    @ManyToOne
    private Assignment assignment;


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

}
