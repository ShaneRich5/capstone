package controllers;

import models.Course;
import models.Role;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.courses.*;
import views.html.pages.home;

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

    public Result show(String code) {
        Course course = Course.find.where()
                .eq("code", code)
                .findUnique();

        if (null == course) return redirect(routes.CoursesCtrl.all());
        User currentUser = User.find.where()
                .eq("idNum", session("id"))
                .findUnique();

        return ok(show.render(course, currentUser));
    }

    public Result assign(String code)
    {

        if(session("role").equals("Administrator"))
        {
            return ok(assign.render(code));
        }else return ok(home.render());

    }

    public Result saveAssign()
    {
        if(session("role").equals("Administrator")) {
            DynamicForm requestData = formFactory.form().bindFromRequest();

            final String id = requestData.get("id");
            final String code = requestData.get("code");
            User lect = User.find.where().eq("idNum", id).eq("role", Role.find.where().eq("name", "Lecturer").findUnique()).findUnique();
            if (lect != null) {
                Course c = Course.findByCode(code);
                lect.getCourses().add(c);
                lect.update();
                c.addParticipant(lect);
                c.update();
            }
            return show(code);
        }
        return redirect("/");
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
