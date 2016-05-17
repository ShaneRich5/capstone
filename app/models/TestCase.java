package models;

import com.avaje.ebean.Model;
import models.Forms.AssignmentForm;
//import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by uhhh on 5/16/16.
 */
@Entity
@Table(name = "testcases")
public class TestCase extends Model
{
    public static Finder<Long, Test> find = new Finder<>(Test.class);

    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="CASE_SEQ")
    public Long id;

    public String name;

    public String markschemeDescription;

    @Constraints.Required
    public int mark;

    @ManyToOne
    public Test test;

    public String code;

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

    public static ArrayList<TestCase> javaTestCasesFromFile(File testCase, Test t) throws IOException {
        ArrayList<TestCase> returnList = new ArrayList<>();
        String file = FileUtils.readFileToString(testCase);
        Scanner scanner = new Scanner(file);
        String current = scanner.next();
        while(scanner.hasNext())
        {
            if(current.equals("@Test"))
            {
                System.out.println("Found Test");
                while (!(current.contains("(") && current.contains(")")))
                    current = scanner.next();

                if(current.contains("(") && current.contains(")"))
                {
                    String name = current.split("\\(")[0];
                    String code = "";
                    System.out.println("Name: "+name);
                    String split[] = current.split("\\)");
                    if(split.length >0)
                        current = split[split.length-1];
                    int closeCount = -1;
                    while (closeCount != 0)
                    {
                        System.out.println("current: "+current);

                        code += current;
                        int matches = StringUtils.countMatches(current,'{');
                        System.out.println("matches{: "+matches);
                        if(closeCount == -1)
                        {
                            if(matches > 0)
                                closeCount += 1;

                        }
                        if(matches > 0)
                            closeCount += matches;

                        matches = StringUtils.countMatches(current,'}');
                        if(matches > 0)
                            closeCount -= matches;
                        if (closeCount != 0)
                            current = scanner.next();
                        else {
                            split = current.split("}");
                            if(split.length >0)
                            current = split[split.length-1];
                        }
                        System.out.println("count: "+closeCount);
                    }
                    TestCase testCase1 = new TestCase(name,"",0,code);
                    System.out.println("Added Test: "+name);
                    testCase1.setTest(t);
                    returnList.add(testCase1);
                }
            }else
                current = scanner.next();
        }
        return returnList;

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

    public Long getId() {
        return id;
    }
}
