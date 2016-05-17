package models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by uhhh on 5/17/16.
 */
public class SubmissionResult {

    @Id
    @GeneratedValue(strategy=SEQUENCE, generator="SUB_SEQ")
    public Long id;

    @ManyToOne
    public Submission submission;

    @ManyToOne
    public TestCase testCase;


    public boolean passed;

    public SubmissionResult(Submission submission, TestCase testCase, boolean passed) {
        this.submission = submission;
        this.testCase = testCase;
        this.passed = passed;
    }
}
