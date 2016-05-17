package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by uhhh on 5/16/16.
 */
@Entity
@Table(name = "testcases")
public class TestCase extends Model
{
    private String code;

    private String markschemeDescription;

    @ManyToOne
    private Test test;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
