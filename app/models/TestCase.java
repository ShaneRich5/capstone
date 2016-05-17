package models;

import com.avaje.ebean.Model;
import models.Forms.AssignmentForm;
import org.apache.commons.io.IOUtils;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by uhhh on 5/16/16.
 */
@Entity
@Table(name = "testcases")
public class TestCase extends Model
{
    public static Finder<Long, Test> find = new Finder<>(Test.class);
    @Id
    private String name;

    private String markschemeDescription;

    @Constraints.Required
    private int mark;

    @ManyToOne
    private Test test;

    private String code;

    public TestCase(String name, String markschemeDescription, int mark, String code) {
        this.name = name;
        this.markschemeDescription = markschemeDescription;
        this.mark = mark;
        this.code = code;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getMarkschemeDescription() {
        return markschemeDescription;
    }

    public void setMarkschemeDescription(String markschemeDescription) {
        this.markschemeDescription = markschemeDescription;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static TestCase javaTestCaseFromForm(File testCase, AssignmentForm form, int number, Test test) throws IOException {
        TestCase returnCase = null;
        FileInputStream inputStream = new FileInputStream(testCase);
        String code = IOUtils.toString(inputStream);
        inputStream.close();
        switch (number)
        {
            case 0:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark0),code);
                break;
            case 1:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark1),code);
                break;
            case 2:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark2),code);
                break;
            case 3:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark3),code);
                break;
            case 4:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark4),code);
                break;
            case 5:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark5),code);
                break;
            case 6:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark6),code);
                break;
            case 7:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark7),code);
                break;
            case 8:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark8),code);
                break;
            case 9:
                returnCase = new TestCase(form.name0,form.description0,Integer.parseInt(form.mark9),code);
                break;
            default:
                return null;
        }
        returnCase.setTest(test);
        return returnCase;
    }
}
