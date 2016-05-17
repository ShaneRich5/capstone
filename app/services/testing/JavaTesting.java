package services.testing;

import models.Submission;
import models.SubmissionResult;
import models.Test;
import models.TestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by uhhh on 5/17/16.
 */
public class JavaTesting {

    public String generateSuite(String tests[])
    {
        StringBuilder builder = new StringBuilder();
        builder.append("import org.junit.runner.RunWith;\n" +
                       "import org.junit.runners.Suite;\n\n");
        builder.append("@RunWith(Suite.class)\n" +
                       "@Suite.SuiteClasses({ ");
        for(int i = 0; i < tests.length; ++i)
        {
            builder.append(tests[i]).append(".class");
            if(i != tests.length-1)
                builder.append(",");
        }
        builder.append("\n})");
        builder.append("public class JunitTestSuite {\n" +
                "}");
        return builder.toString();
    }

    public String generateRunner(String testFile)
    {
        String split[] = testFile.split("\\.java");
        testFile = split[0];
        StringBuilder builder = new StringBuilder();
       builder.append("import org.junit.runner.JUnitCore;\n" +
               "import org.junit.runner.Result;\n" +
               "import org.junit.runner.notification.Failure;\n" +
               "\n" +
               "public class TestRunner {\n" +
               "   public static void main(String[] args) {\n" +
               "      Result result = JUnitCore.runClasses(").append(testFile).append(".class);\n" +
               "       System.out.println(\"AutoGraderStart\");\n" +
               "      for (Failure failure : result.getFailures()) {\n" +
               "         System.out.println(failure.getTestHeader());\n" +
               "      }\n" +
               "   }\n" +
               "}  \t\n");
        return builder.toString();
    }

    public ArrayList<SubmissionResult> unitTest(String driver, String testCases, String source, Test t, Submission s) throws IOException, InterruptedException {
        ProcessBuilder ps=new ProcessBuilder("javac "+driver+" "+testCases+" "+source);
        ps.redirectErrorStream(true);
        Process pr = ps.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        pr.waitFor();
        in.close();
        ps=new ProcessBuilder("java "+driver.split("\\.java")[0]);
        ps.redirectErrorStream(true);
        pr = ps.start();
        in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        ArrayList <SubmissionResult> results = new ArrayList<>();
        boolean start = false;

        while ((line = in.readLine()) != null) {
            System.out.println(line);
            if(line.equals("AutoGraderStart"))
                start = true;
            if(start)
                for(TestCase testCase: t.getTestCases())
                {
                    if(line.contains(testCase.name))
                        results.add(new SubmissionResult(s,testCase,false));
                }
        }
        for(TestCase testCase: t.getTestCases())
        {
            boolean found = false;
            for(SubmissionResult submissionResult: results)
                if(submissionResult.testCase.equals(t))
                    found = true;
            if(!found)
                results.add(new SubmissionResult(s,testCase,true));
        }
        return results;

    }




}
