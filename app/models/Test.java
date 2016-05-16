package models;

import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by uhhh on 5/16/16.
 */
public class Test {

    public String testHeader;

    @OneToMany
    public List<TestCase> testCases;
}
