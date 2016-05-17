package controllers;

import com.google.inject.Inject;
import models.*;
import models.Forms.AssignmentForm;
import org.apache.commons.io.*;
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
        Http.MultipartFormData.FilePart<File> tcases = body.getFile("case");
        File f = tcases.getFile();
        try {
            t.fullTest = FileUtils.readFileToString(f);
            ArrayList<TestCase> tcs = TestCase.javaTestCasesFromFile(f,t);
            testCases = tcs;
        }catch (Exception e){return internalServerError();}
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
        assignment.setPath("");
        t.setAssignment(assignment);

//        assignment.setPath(path);
        assignment.save();
        t.save();
        String path = "assignments/"+(Assignment.find.nextId()-1)+"/"+assignmentFilePart.getFilename();
        assignment.setPath(path);
        assignment.update();
        try {

            FileUtils.moveFile(assignmentFile, new File(path));
        }
        catch (Exception e){return internalServerError();}
        course.assignments.add(assignment);

        course.update();

        return ok(finalize.render(t));
    }

    public Result download(long id)
    {
        Assignment assignment = Assignment.find.byId(id);
        if (null == assignment) return ok("Assignment not found");
//        User u = ;
        if(assignment.course.participants.contains(User.find.where().eq("idNum",session().get("id")).findUnique()))
        {
            return ok(new File(assignment.getPath()));
        }else
            return redirect("/");
    }

    public Result show(long id) {
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
