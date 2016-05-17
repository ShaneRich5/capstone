package controllers;

import com.google.inject.Inject;
import models.*;
import models.Forms.AssignmentForm;
import org.apache.commons.io.FileUtils;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.assignments.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shane on 3/15/16.
 */
public class AssignmentsCtrl extends Controller {

    @Inject
    FormFactory formFactory;

    public Result all(String name) {
        List<Assignment> assignments = Assignment.find.all();
        return ok("<h1>Done</h1>");
    }

    public Result create(String name) {
        Course course = Course.findByName(name);
        return ok(create_prototype.render(course));
    }

    public Result store(String courseCode) {
//        if (! session().get("role").equals("lecturer")) return redirect("/login");
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Form<AssignmentForm> form = formFactory.form(AssignmentForm.class).bindFromRequest();
        AssignmentForm formFields = form.get();

        ArrayList<TestCase> testCases = new ArrayList<>();
        Test t = null;
        t = new Test();
        Http.MultipartFormData.FilePart<File> assignmentFilePart = body.getFile("assignment");
        for(int i = 0; i < formFields.amount; ++i) {
            Http.MultipartFormData.FilePart<File> tcase = body.getFile("case"+String.valueOf(i));
            File f = tcase.getFile();
            try {
                TestCase tc = TestCase.javaTestCaseFromForm(f, formFields, i, t);
                testCases.add(tc);
            }catch (Exception e){return internalServerError();}
        }
        t.setTestCases(testCases);

        File assignmentFile = assignmentFilePart.getFile();
        

        Course course = Course.findByCode(courseCode);
        User lecturer = User.find.where().eq("idNum",session().get("id")).findUnique();
        Language language = Language.find.where().eq("name",formFields.language).findUnique();

        if (null == course) return ok("Course not found");
        if (null == lecturer) return ok("Lecturer not found");
        if (null == language) return ok("Language not found");


        Assignment assignment = new Assignment(formFields.name, formFields.description);
        assignment.course = course;
        assignment.lecturer = lecturer;
        assignment.language = language;
        assignment.tests.add(t);
        String path = "assignments/"+assignment.course.code+"/"+assignmentFile.getName();
        assignment.setPath(path);
        assignment.save();

        try {
            FileUtils.moveFile(assignmentFile, new File(path));
        }catch (Exception e){return internalServerError();}
        course.assignments.add(assignment);
        course.update();

        return redirect(routes.AssignmentsCtrl.show(course.name, assignment.id));
    }

    public Result show(String name, long id) {
        Assignment assignment = Assignment.find.byId(id);

        if (null == assignment) return ok("Assignment not found");

        return ok(show.render(assignment));
    }

    public Result edit(String name) {

        return ok("Edit form");
    }

    public Result update(String name) {

        return ok("Respond to put/patch");
    }

    public Result destroy(String name) {

        return ok("Delete assignment");
    }
}
