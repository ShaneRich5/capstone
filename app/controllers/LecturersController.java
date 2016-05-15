package controllers;

import models.Course;
import models.Role;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.lecturers.create;
import views.html.lecturers.index;
import views.html.lecturers.show;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shane on 3/15/16.
 */
public class LecturersController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result all() {
        List<User> lecturers = new ArrayList<>();
        if(session("role").equals("Administrator"))
            lecturers = User.find.where().eq("role", Role.find.where().eq("name","Lecturer").findUnique()).findList();
        return ok(index.render(lecturers));
    }

    public Result create() {
        return ok(create.render());
    }

    public Result store() {
        DynamicForm requestData = formFactory.form().bindFromRequest();

        final String id = requestData.get("id");
        if(session("role").equals("Administrator"))
        {
            User l = User.find.where().idEq(id).findUnique();
            Role lecturerRole = Role.find.where().eq("name","Lecturer").findUnique();
            if(l == null)
            {
                l = new User(id,"","");
                l.setRole(lecturerRole);
                l.save();
            }else
            {
                l.setRole(lecturerRole);
                l.update();
            }
        }
        return redirect("/lecturers");
    }

    public Result show(String id) {
        User lecturer = User.find.where()
                .eq("idNum", id)
                .findUnique();

        if (null == lecturer) return all();
        return ok(show.render(lecturer));
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
