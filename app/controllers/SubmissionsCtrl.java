package controllers;

import models.*;
import org.apache.commons.io.FileUtils;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.testing.JavaTesting;
import views.html.submissions.*;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shane on 3/15/16.
 */
public class SubmissionsCtrl extends Controller {

    @Inject
    FormFactory dynamicForm;

    public Result all(String name, long id) {
        return ok(index.render(Assignment.find.byId(id).submissions));
    }

    public Result create(long id) {
        Assignment assignment = Assignment.find.byId(id);
        User student = User.find.where().eq("idNum",session().get("id")).findUnique();
        if(assignment == null) return ok("Not found");
//        if(assignment.course.participants.contains(student))
//        {
//
//        }
        return ok(create.render(assignment));
    }

    public Result store(long id) {
        User student = User.find.where().eq("idNum",session().get("id")).findUnique();
        Assignment assignment = Assignment.find.byId(id);

        if (null == student) return ok("Please login");

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> program = body.getFile("program");

        if (null == program) return ok("expected a file submission");

        String fileName = program.getFilename();
        String contentType = program.getContentType();
        File file = program.getFile();

        String uploadPath = "/assignments/" + assignment.id +
                "/submissions/" + student.getIdNum()+"/";


        File dir = new File(uploadPath);
        System.out.println(dir.getAbsolutePath());
        try {
            FileUtils.forceMkdir(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File newFile = new File(uploadPath+fileName);

        //boolean saved = file.renameTo();
        Submission s = new Submission(student,assignment,assignment.course,null,uploadPath+fileName,0);
        try {
            FileUtils.copyFile(file,newFile);
            ArrayList<SubmissionResult> srs = Submission.gradeSubmission(s);
            int grade = 0;
            for(TestCase t: s.assignment.tests.get(0).getTestCases())
            {
                for(SubmissionResult sr: srs)
                {
                    if(sr.testCase.equals(t) && sr.passed)
                        grade += t.mark;
                }
            }
            s.grade = grade;
            s.results = srs;
            s.save();
            student.addSubmission(s);
            student.update();

        }catch (Exception e){e.printStackTrace();}
        return ok("name: " + fileName+" Grade: "+s.grade);
    }

    public Result show(String name, long courseId, long submissionId) {

        return ok("Show");
    }

    public Result edit(String name, long courseId) {

        return ok("Edit form");
    }

    public Result update(String name, long courseId) {

        return ok("Respond to put/patch");
    }

    public Result destroy(String name, long courseId) {

        return ok("Delete assignment");
    }
}
