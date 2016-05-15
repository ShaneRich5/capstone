package controllers;

import models.Course;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.courses.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shane on 3/15/16.
 */
public class CoursesCtrl extends Controller {

    @Inject
    FormFactory formFactory;

    public Result all() {
        List<Course> courses = new ArrayList<>();
        if(session("role").equals("Administrator"))
            courses = Course.find.all();
        else if(session("id") != null)
        {
            courses = User.find.where().eq("idNum",session("id")).findUnique().courses;
        }

        return ok(index.render(courses));
    }

    public Result create() {
        return ok(create.render());
    }

    public Result store() {
        DynamicForm requestData = formFactory.form().bindFromRequest();

        final String name = requestData.get("name").toLowerCase();
        final String description = requestData.get("description");

        Course course = new Course(name, description,Course.codeFromName(name));
        course.save();

        return redirect("/courses");
    }

    public Result show(String name) {
        Course course = Course.find.where()
                .eq("name", name.toLowerCase())
                .findUnique();

        if (null == course) return redirect(routes.CoursesCtrl.all());

        String email = session().get("email");

        User currentUser = User.find.where()
                .eq("email", email)
                .findUnique();



        return ok(show.render(course, currentUser));
    }

    public Result join(String name) {
        String email = session().get("email");

        if (null == email) return redirect(routes.CoursesCtrl.show(name));

        Course course = Course.findByName(name);
        User user = User.findByEmail(email);

        course.participants.add(user);
        course.save();

        return redirect(routes.CoursesCtrl.show(name));
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
