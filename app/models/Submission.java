package models;

import com.avaje.ebean.Model;
import com.avaje.ebeaninternal.server.lib.util.Str;
import org.apache.commons.io.FileUtils;
import play.data.validation.Constraints;
import services.testing.JavaTesting;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

    @OneToMany
    private ArrayList<SubmissionResult> results;

    /*
    Relationship
     */
    @ManyToOne(cascade = CascadeType.ALL)
    public Course course;

    @ManyToOne(cascade = CascadeType.ALL)
    public Assignment assignment;

    @ManyToOne(cascade = CascadeType.ALL)
    public User student;

    public static SubmissionResult gradeSubmission(Submission submission) throws IOException {
        if(submission.assignment.language.equals("Java"))
        {
            JavaTesting javaTesting = new JavaTesting();
            String path = submission.student.getIdNum()+"/"+submission.course.code+"/"+submission.assignment.name+"/";
            File testFile = new File(path+"/submissiontest.java");
            File assignmentFile = new File(submission.path);
            File newAssignmentFile = new File(path+"/"+assignmentFile.getName());
            FileUtils.copyFile(assignmentFile,newAssignmentFile);
            File driver = new File(path+"/TestRunner.java");
            FileUtils.write(driver,javaTesting.generateRunner("submissiontest.java"));
            FileUtils.write(testFile,submission.assignment.tests.get(0).fullTest);

        }
        return null;
    }

}
