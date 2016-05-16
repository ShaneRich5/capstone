package controllers;

import com.google.inject.Inject;
import models.Assignment;
import models.Course;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.assignments.*;

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

    public Result store(String courseName) {
        if (! session().get("role").equals("lecturer")) return redirect("/login");

        DynamicForm requestData = formFactory.form().bindFromRequest();

        if (requestData.data().size() == 0) return badRequest("Expected data");

        final String assignmentName = requestData.get("name");
        final String assignmentDescription = requestData.get("description");

        Course course = Course.findByName(courseName);
        User lecturer = User.findByEmail(session().get("email"));

        if (null == courseName) return ok("Course not found");
        if (null == lecturer) return ok("Lecturer not found");


        Assignment assignment = new Assignment(assignmentName, assignmentDescription);
        assignment.course = course;
        assignment.lecturer = lecturer;
        assignment.save();

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
