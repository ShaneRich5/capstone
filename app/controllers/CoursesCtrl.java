package controllers;

import models.Course;
import play.data.DynamicForm;
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
    DynamicForm dynamicForm;

    public Result all() {
        List<Course> courses = Course.find.all();
        return ok(index.render(courses));
    }

    public Result create() {
        return ok(create.render());
    }

    public Result store() {
        dynamicForm.bindFromRequest();
        return redirect("/");
    }

    public Result show(long id) {

        return ok("Show");
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
