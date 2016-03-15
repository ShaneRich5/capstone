package controllers;

import models.Assignment;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.assignments.*;

import java.util.List;

/**
 * Created by shane on 3/15/16.
 */
public class AssignmentsCtrl extends Controller {

    public Result index() {
        List<Assignment> assignments = Assignment.find.all();
        return ok(index.render(assignments));
    }

    public Result create() {
        // TODO
        return ok(create.render());
    }

    public Result store() {
        // TODO
        return redirect("/");
    }

    public Result show() {

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
