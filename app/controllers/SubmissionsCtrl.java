package controllers;

import models.*;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.testing.JavaTesting;
import views.html.submissions.*;

import javax.inject.Inject;
import java.io.File;
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

    public Result create(String name, long id) {
        return ok(create.render(Assignment.find.byId(id)));
    }

    public Result store(String name, long id) {
        User student = User.find.where().eq("idNum",session().get("id")).findUnique();
        Assignment assignment = Assignment.find.byId(id);

        if (null == student) return ok("Please login");

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> program = body.getFile("program");

        if (null == program) return ok("expected a file submission");

        String fileName = program.getFilename();
        String contentType = program.getContentType();
        File file = program.getFile();

        String uploadPath = "/programs" +
                "/courses/" + assignment.course.code +
                "/assignments/" + assignment.id +
                "/submissions/" + student.getIdNum();

        File dir = new File(uploadPath);

        if (! dir.exists()) {
            System.out.println("Creating dir: " + uploadPath);
            boolean saved = dir.mkdir();

            System.out.println((saved) ? "dir created" : "failed to make dir");
        }

        boolean saved = file.renameTo(new File(uploadPath, fileName));
        Submission s = new Submission(student,assignment,assignment.course,null,uploadPath+"/"+fileName,0);
        try {
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

        }catch (Exception e){e.printStackTrace();}

        if (!saved) return ok("Unable to save file");

        return ok("name: " + fileName);
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
