package controllers;

import models.Course;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.courses.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by shane on 3/15/16.
 */
public class CoursesCtrl extends Controller {

    @Inject
    FormFactory formFactory;

    public Result all() {
        List<Course> courses = Course.find.all();

        return ok(index.render(courses));
    }

    public Result create() {
        return ok(create.render());
    }

    public Result store() {
        DynamicForm requestData = formFactory.form().bindFromRequest();

        final String name = requestData.get("name");
        final String description = requestData.get("description");

        Course course = new Course(name, description);
        course.save();

        return redirect("/courses");
    }

    public Result show(String name) {
        Course course = Course.find.where().eq("name", name).findUnique();
        return ok(show.render(course));
    }

    public Result edit() {

        return ok("Edit form");
    }

    public Result update() {

        return ok("Respond to put/patch");
    }

    public Result destroy() {

        return ok("Delete assignment");
    }
}
