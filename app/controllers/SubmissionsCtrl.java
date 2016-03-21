package controllers;

import models.Assignment;
import models.User;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.submissions.*;

import javax.inject.Inject;
import java.io.File;

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
        User student = User.findByEmail(session().get("email"));
        Assignment assignment = Assignment.find.byId(id);

        if (null == student) return ok("Please login");

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> program = body.getFile("program");

        if (null == program) return ok("expected a file submission");

        String fileName = program.getFilename();
        String contentType = program.getContentType();
        File file = program.getFile();

        String uploadPath = "/programs" +
                "/courses/" + assignment.course.name +
                "/assignments/" + assignment.id +
                "/submissions/" + student.getName();

        uploadPath = uploadPath.toLowerCase();

        File dir = new File(uploadPath);

        if (! dir.exists()) {
            System.out.println("Creating dir: " + uploadPath);
            boolean saved = dir.mkdir();

            System.out.println((saved) ? "dir created" : "failed to make dir");
        }

        boolean saved = file.renameTo(new File(uploadPath, fileName));

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
